$( document ).ready(function() {

    var url = window.location;

    // SUBMIT FORM
    $("#save-user").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){
    var firstName = $("#firstname").val();
    var lastName = $("#lastname").val();
    debugger
        var formData = {
            "firstname": firstName,
            "lastname": lastName,
            "email": $("#email").val(),
            "password": $("#password").val(),
            "role": $("#role").val(),
            "position": $("#position").val(),
        }

        // DO POST            console.log(result)
        console.log(formData);
        $.ajax({
            url : "/event/add-user",
            method : "POST",
            data : formData,

            success : function(result) {
                if (Object.keys(result).length !== 0){
                    $.each(result, function(i, string){
                        var error = "Hiba " + i + ": " + string ;
                        $('#getResultDiv .list-group').append('<li><h4 class="list-group-item">'+error+'</h4></li>')
                    });
                    console.log(result)
                // }else {
                //     debugger;
                //     alert(Object.keys(result).length);
                //
                //     $('#getResultDiv').html(`<p>${firstName} + ${lastName}</p>` );
                // }
                    alert("malacka");

                console.log(result);
            }
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