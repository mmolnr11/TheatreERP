$( document ).ready(function() {

    var url = window.location;

    // SUBMIT FORM
    $("#search-event").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){
        var searchdatestart = $("#searchdatestart").val();
        var searchdateend = $("#searchdateend").val();
        var formData = {
            "searchdatestart": searchdatestart,
            "searchdateend": searchdateend
            // searchdatestart: searchdatestart,
            // searchdateend: searchdateend
        };

        console.log(formData);
        $.ajax({
            // contentType : "application/json",
            // dataType : 'json',
            url : "/event/search",
            method : "POST",
            data : formData,
            success : function(result) {
                debugger;

                console.log(result);
                if (Object.keys(result).length !== 0){
                    $.each(result, function(i, event){
                        var startDate = new Date(event.startDateTime);
                        var endDate = new Date(event.endDateTime);
                        // console.log(myDate.toUTCString());
                        var eventId = event.id;
                        var date = "Esemenyek : " + event.title + " " + startDate.toUTCString() + " " +
                            endDate.toUTCString() + " "+ event.description;
                        // var link = "<a href='/event/" + eventId +"/description'>This is the link</a>";

                        $('#searchDiv .list-group').append('<li><a  href="event/'+ eventId + '/description"  class="list-group-item">'+date+'</a></li>')
                    });
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

        resetData();

    }

    function resetData(){
        $("#employee").val("");
    }
})