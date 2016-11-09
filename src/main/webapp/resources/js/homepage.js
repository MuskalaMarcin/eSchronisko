$(document).ready(function () {
    _setLogo()
    _fillWholePage()
    $(window).resize(_fillWholePage);
})

function _setLogo() {
    var logoBackgrounds = ['logoDog.jpg', 'logoCat.jpg']
    var randomBackground = Math.floor(Math.random() * logoBackgrounds.length);
    $('.logo').css('background-image', 'url(../../resources/images/logo/' + logoBackgrounds[randomBackground] + ')');
}

function _fillWholePage(){
    console.log("resize");
}

function scrollPageToTop() {
    $("html, body").animate({scrollTop: 0}, 300);
}
