//List item transform
var transform = {"<>":"li","html":[
                    {"<>":"span","html":" <b>${name}</b>: "},
                    {"<>":"span","html":" born: ${DOB}"},
                    {"<>":"span","html":" SSN: ${SSN}"}
                ]};
$(function(){

$(document).ready(function() {
    $.ajax({
        url: "/students/list"
    }).then(function(data) {
        console.log(data);

    //Create the list
    $('.student-content').json2html(data,transform);
    });
});
});
