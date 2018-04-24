// TODO
$( document ).ready(function() {
	
	var url = window.location;
	
	// SUBMIT FORM
    $("#assigneEmployeeForm").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPost();
	});
    
    
    function ajaxPost(){
    	var userDropdown = $("#employee");
    	var selectedUserId = userDropdown.val();
        var dateId = $("#divComment").attr("class");
    	var formData = {
    		'employeeId' : selectedUserId,
			'dateId' : dateId
    	};
    	
        console.log(formData);
        $.ajax({
            url : "/addEmployee",
            method : "POST",
            data : formData,
			success : function(result) {
				if(Object.keys(result).length !== 0){
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