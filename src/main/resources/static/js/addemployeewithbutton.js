$( document ).ready(function() {
	
	var url = window.location;
	
	// SUBMIT FORM
    $("#customerForm").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPost();
	});
    
    
    function ajaxPost(){
    	var userDropdown = $("#employee");
    	var selectedUserId = userDropdown.val();
        var eventId = $("#divComment").attr("class");
    	var formData = {
    		'employeeId' : selectedUserId,
			'eventId' : eventId
    	}
    	
    	// DO POST            console.log(result)
        console.log(formData);
        $.ajax({
            url : "/addEmployee",
            method : "POST",
            // contentType : "application/json",
            data : formData,
			// data : JSON.stringify(formData),
			// dataType : 'json',
			success : function(result) {
				if(Object.keys(result).length !== 0){
					// $("#postResultDiv").html(
					// 	"<strong>" + "Post Successfully! Customer's Info: FirstName = "
					// 		+ result.data + "</strong>");
                    userDropdown.find('option[value='+ selectedUserId +']').remove();
                    userDropdown.find('option:first').attr('selected', 'selected');

				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				console.log(result);
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
    	
    	// Reset FormData after Posting
    	resetData();

    }
    
    function resetData(){
    	$("#employee").val("");
    }
})