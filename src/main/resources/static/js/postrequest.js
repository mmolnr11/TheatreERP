$( document ).ready(function() {
	
	var url = window.location;
	
	// SUBMIT FORM
    $("#customerForm").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPost();
	});
    
    
    function ajaxPost(){
    	
    	// PREPARE FORM DATA
    	var formData = {
    		firstname : $("#kk").val(),
    	}
    	
    	// DO POST            console.log(result)
        console.log('gyik');
        $.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/postcustomer",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				if(result.status == "Done"){
					$("#postResultDiv").html("<strong>" + "Post Successfully! Customer's Info: FirstName = " 
							+ result.data.firstname + " ,LastName = " + result.data.lastname + "</strong>");
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
    	$("#firstname").val("");
    	$("#lastname").val("");
    }
})