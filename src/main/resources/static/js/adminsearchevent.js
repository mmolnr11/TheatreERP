$( document ).ready(function() {

    var url = window.location;

    // SUBMIT FORM
    $("#search-event-admin").submit(function(event) {
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
        };

        $.ajax({
            url : "/admin/event/search",
            method : "POST",
            data : formData,
            success : function(searchResult) {

                if (Object.keys(searchResult).length !== 0){
                    console.log(searchResult);
                    $('#searchDiv .list-group').empty();
                    $.each(searchResult, function(i, time){
                        var title = time.event.title;
                        var eventId = time.event.id;
                        var start = time.datesOfEvent.startDate;
                        var end = time.datesOfEvent.endDate;
                        var startDate = new Date(start) ;
                        var endDate = new Date(end);
                        var dateId = time.datesOfEvent.id;
                        $('#searchDiv2 .list-group').append('<li><a  href="admin/event/'+eventId+ "/date/"+ dateId + '"  class="list-group-item">'+ title +" "+ startDate +" " + endDate +'</a></li>')

                    });
                }
                else {
                    debugger;
                    alert(Object.keys(searchResult).length);

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