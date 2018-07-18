//----------------Header-----------------------------
function hoverIconFollow() {
    $(".social-link").mouseenter(function () {
        $(this).animate({"backgroundPositionY": "-242px"}, "slow");
    });

    $(".social-link").mouseleave(function () {
        $(this).animate({"backgroundPositionY": "-208px"}, "slow");
    });

    $(".facebook-link").mouseenter(function () {
        $(this).animate({"backgroundPositionY": "-199px"}, "slow");
    });

    $(".facebook-link").mouseleave(function () {
        $(this).animate({"backgroundPositionY": "-165px"}, "slow");
    });

    $(".youtube-link").mouseenter(function () {
        $(this).animate({"backgroundPositionY": "-163px"}, "slow");
    });

    $(".youtube-link").mouseleave(function () {
        $(this).animate({"backgroundPositionY": "-130px"}, "slow");
    });
}

function showHideNavigationMenu() {
    $(".header-bottom").mouseenter(function () {
        console.log("showHideNavigationMenu show");
        $("#navigation-menu").show();
    });

    $(".header-bottom").mouseleave(function () {
        console.log("showHideNavigationMenu hide");
        $("#navigation-menu").hide();
    });
}

$(document).ready(function () {
    console.log("load header js file");
    hoverIconFollow();
    showHideNavigationMenu();
});

//------------------------------------------------------------