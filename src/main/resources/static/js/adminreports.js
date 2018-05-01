$(document).ready(function() {

    $('.emp-hours').css('color', function() {
        debugger;
        var text = $(this).text();
        console.log(text);
        var hours = parseFloat(text);
        return (hours == 0 ? 'red' : 'green');
    });

});