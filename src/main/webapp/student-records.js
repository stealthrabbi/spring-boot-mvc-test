$(document).ready(function() {
    $.ajax({
        url: "/students/list"
    }).then(function(data) {
        console.log(data);
       $('.student-content').append(JSON.stringify(data));
    });
});