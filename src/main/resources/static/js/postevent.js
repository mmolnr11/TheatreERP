$( document ).ready(function() {

    var url = window.location;

    // SUBMIT FORM
    $("#save-event").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){
        debugger;
        var str = $( "#save-event" ).serialize();
        $( "#results" ).text( str );
        console.log(str);

        var title = $("#title").val();
        var formData = {
            // "timepickerdate": $("#timepickerdate").val(),
            "serialize" : str,
            "date": $("#date").val(),
            "title": title,
            "description": $("#description").val(),
            "location": $("#location").val(),
            "startDateTime": $("#startDateTime").val(),
            "endDateTime": $("#endDateTime").val(),
            "type": $("#type").val()
        };

        // DO POST            console.log(result)
        console.log(formData);
        $.ajax({
            url : "/event/add-event",
            method : "POST",
            data : formData,

            success : function(result) {
                if (Object.keys(result).length !== 0){
                    $.each(result, function(i, string){
                        var error = "Hiba " + i + ": " + string ;
                        $('#eventErrors .list-group').append('<li><h4 class="list-group-item">'+error+'</h4></li>')
                    });
                    console.log(result);
                }
                else {
                    debugger;
                    // alert(Object.keys(result).length);
                    $('#eventErrors .list-group').append('<li><h4 class="list-group-item">'+title + " sikeresen hozzadava"+'</h4></li>');
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