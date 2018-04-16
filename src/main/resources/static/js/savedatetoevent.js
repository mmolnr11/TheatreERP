
    $(document).on("click","#addDateButton",function (){
        var title = $("#title").text();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        var dayOfEvent = $("#dayOfEvent").val();
        var eventId = $("#addDateButton").attr("name");
        debugger;
        var formData = {
            "startTime": startTime,
            "endTime": endTime,
            "dayOfEvent" : dayOfEvent,
            "eventId" : eventId
        };

        console.log(formData);
        $.ajax({
            // contentType : "application/json",
            // dataType : 'json',
            url : "/admin/event/addDate",
            method : "POST",
            data : formData,
            success : function(result) {


                console.log(result);
                if (Object.keys(result).length !== 0){
                    console.log(result.startDate);
                    console.log(result.id);
                    var start = new Date(result.startDate);
                    var link = '<a href="admin/event/'+ eventId + "/date/"+ result.id+'">';
                    // var link = '<a href="admin/event/'+ eventId + "/date/"+ result.id+'">';
                    $("#datesToEvent").append(link + title + " "+
                        dayOfEvent + " " +
                        startTime + ":00.0 "+
                        dayOfEvent + " " +
                        endTime +":00.0 "+ '</a>')

                    // $.each(result, function(i, event){
                    // });
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


    });

