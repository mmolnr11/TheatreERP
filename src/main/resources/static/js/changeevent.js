$( document ).ready(function() {

    var url = window.location;

    // SUBMIT FORM
    $("#update-event").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){
        debugger;
        var title = $("#update-title").val();
        var str = $( "#update-event" ).serialize();

        var formData = {
            // "startdate": $("#update-startdate").val(),
            // "date": $("#update-date").val(),
            "title": title,
            "serialize" : str,
            "description": $("#update-description").val(),
            "location": $("#update-location").val(),
            // "startDateTime": $("#update-startDateTime").val(),
            // "endDateTime": $("#update-endDateTime").val(),
            "type": $("#update-type").val(),
            "id": $("#update-id").val()
        };

        // DO POST            console.log(result)
        console.log(formData);
        $.ajax({
            url : "/admin/event/change",
            method : "POST",
            data : formData,

            success : function(result) {
                if (Object.keys(result).length !== 0){
                    alert(result.title + " megváltoztatva");
                    // $.each(result, function(i, string){
                    //     var error = "Hiba " + i + ": " + string ;
                    //     $('#getResultDiv .list-group').append('<li><h4 class="list-group-item">'+error+'</h4></li>')
                    // });
                    console.log(result)
                }
                else {
                    debugger;
                    alert(Object.keys(result).length);

                    $('#getResultDiv .list-group').append('<li><h4 class="list-group-item">'+firstname +" "+  lastname +" sikeresen hozzadava"+'</h4></li>');
                }
            },
            error : function(e) {
                alert("Error!");
                console.log("ERROR: ", e);
            }
        });

        // Reset FormData after Posting
        resetData();

    }

    function resetData(){
        $("#employee").val("");
    }
});