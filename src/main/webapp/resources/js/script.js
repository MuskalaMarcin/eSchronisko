$(document).ready(function () {
    _setLogo()
    $('.carousel').carousel({
        interval: 2000
    })
})

function _setLogo() {
    var logoBackgrounds = ['logoDog.jpg', 'logoCat.jpg']
    var randomBackground = Math.floor(Math.random() * logoBackgrounds.length);
    $('#logo').css('background-image', 'url(/resources/images/logo/' + logoBackgrounds[randomBackground] + ')');
}

function scrollPageToTop() {
    $("html, body").animate({scrollTop: 0}, 300);
}

function _drawChart(dataUrl, options, elementId) {
    $.ajax({
        url: dataUrl,
        context: document.body,
        success: function (data) {
            var data = google.visualization.arrayToDataTable(data);
            var chart = new google.visualization.ComboChart(document.getElementById(elementId));
            chart.draw(data, options);
        }
    });
}