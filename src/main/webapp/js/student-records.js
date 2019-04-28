$(document).ready(function() {
    $.ajax({
        url: "students/list"
    }).then(function(data) {
        console.log(data);

        var ul = $('<ul class="w3-ul w3-card-4 w3-green">').appendTo('.student-content');
        var json = data;
        console.log(json);
        // for each json item in the array, create a <li> element with some data
        $(json).each(function(index, item) {
            console.log(item);
            ul.append(
                $(document.createElement('li')).text(item["name"] + " was born " + item["DOB"]));
        });
    });
});
