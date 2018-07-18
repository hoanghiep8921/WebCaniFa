// ----------slide of new product

function setIndicatorActive(indicator) {
    var listIndicatorItems = document.querySelectorAll('.indicator-item');
    for(var i = 0; i < listIndicatorItems.length; i++) {
        var dataItemId = listIndicatorItems[i].getAttribute('data-item-id');
        if(indicator == dataItemId) {
            listIndicatorItems[i].classList.add('indicator-active');
        } else {
            listIndicatorItems[i].classList.remove('indicator-active');
        }

    }
}

function setPositionforDraggable(jQueryObject) {
    var pos1 = parseInt(document.getElementById("draggable").style.left, 10);
    var productItemLength = $('#product-first').innerWidth();
    console.log("draggable " + productItemLength);
    var posOut = 0;
    if(pos1 > 0) {
        posOut = 0;
        setIndicatorActive('0');
    } else if(pos1 < -1340) {
        posOut = -4 * productItemLength;
        setIndicatorActive('1');
    } else {
        posOut = Math.floor(pos1 / productItemLength + 0.5) * productItemLength;
        setIndicatorActive('0');
    }
    jQueryObject.animate({"left": posOut + "px"}, "slow");
}

function processDragDropListProduct(jQueryObject) {
    jQueryObject.draggable({
        axis: "x",
        stop: function (event, ui) {
            setPositionforDraggable($(this));
        }
    });
}


$(document).ready(function () {
    processDragDropListProduct($("#draggable"));
    setIndicatorActive('0');

});



