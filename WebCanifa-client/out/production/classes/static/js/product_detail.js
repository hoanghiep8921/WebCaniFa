function loadCommonFile() {
    $("#header").load("/header", function(responseTxt, statusTxt, xhr){
        if(statusTxt == "success")
            console.log("External content loaded successfully!");
        if(statusTxt == "error")
            console.log("Error: " + xhr.status + ": " + xhr.statusText);
    });

    $("#footer").load("/footer", function(responseTxt, statusTxt, xhr){
        if(statusTxt == "success")
            console.log("External content loaded successfully!");
        if(statusTxt == "error")
            console.log("Error: " + xhr.status + ": " + xhr.statusText);
    });
}

function processPreviousOrNext() {
    var topPos = 0;
    var heightImg = $('.img-product_inner').innerHeight();
    var heightListBox = $('#out-list_product').innerHeight();
    var heightList = $('#img_product_list').innerHeight();
    $("#list-product_previous").click(function () {
        console.log('click list-product_previous');
        if(topPos > (heightListBox - heightList)) {
            topPos -= heightImg;
            $("#img_product_list").animate({"top": topPos + "px"}, "slow");
        }
    });

    $("#list-product_next").click(function () {
        console.log('click list-product_next');
        if(topPos < 0) {
            topPos += heightImg;
            $("#img_product_list").animate({"top": topPos + "px"}, "slow");
        }
    });
}

function addListenerEventForPicture() {
    var productImgList = document.querySelectorAll('.img_product');
    for(var i = 0 ; i < productImgList.length; ++i) {
        productImgList[i].addEventListener('click', processClickImgPicture, false);
    }
}

function processClickImgPicture(event) {
    console.log('processClickImgPicture');
    console.log(event.target.getAttribute('src'))
    $('#picture-focus_pic').attr('src', event.target.getAttribute('src'));
}


$(document).ready(function () {
    loadCommonFile();
    processPreviousOrNext();
    addListenerEventForPicture();
});