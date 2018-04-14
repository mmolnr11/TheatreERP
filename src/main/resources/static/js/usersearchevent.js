$( document ).ready(function() {

    var url = window.location;

    // SUBMIT FORM
    $("#search-event-user").submit(function(event) {
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
            url : "/user/event/search",
            method : "POST",
            data : formData,
            success : function(timeList) {
                debugger;

                console.log(timeList);
                if (Object.keys(timeList).length !== 0){
                    $.each(timeList, function(i, time){
                        var start = new Date(time[0]);
                        var end = new Date(time[1]);
                        $('#searchDiv .list-group').append('<li><a  href="user/even/description"  class="list-group-item">'+ start + end +'</a></li>')

                        // $.each(time, function(i, kk){
                        //     console.log(kk);
                        //
                        //     console.log(start);
                        //
                        // });
                        // alert(time.event.title);
                        // alert(time.event.title);
                        // var title = time.event.title;
                        // var start = time.startDateTime;
                        // var startDate = new Date(event.startDateTime);
                        // var endDate = new Date(event.endDateTime);
                        // var eventId = event.id;
                        // var date = "Esemenyek : " + event.title + " " + startDate + " " +
                        //     endDate + " "+ event.description;
                        // var link = "<a href='/event/" + eventId +"/description'>This is the link</a>";

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