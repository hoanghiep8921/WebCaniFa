
$(document).ready(function () {
    var dataProduct = {};
    var dataCategory = {};
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#preview-product-img').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#input-select-img-product").change(function() {
        readURL(this);
        var formData = new FormData();
        NProgress.start();
        formData.append('file', $("#input-select-img-product")[0].files[0]);
        axios.post("/api/upload/upload-image", formData).then(function(res){
            NProgress.done();
            if(res.data.success) {
                $('#preview-product-img').attr('src', res.data.link);
                dataProduct = {
                    image: res.data.link
                };
            }
        }, function(err){
            NProgress.done();
        })
    });

    $('#datepicker-created-date-product').datetimepicker();

    $("#btn-new-product").on("click", function () {
        dataProduct = {};
        $('#preview-product-img').attr('src', '/img/default-img.png');
        $('#input-product-name').val("");
        $('#input-product-price').val("");
        $('#input-product-quantity').val("");
        $('#input-product-desc').val("");
        $('#product-category').val("1");
        $('#product-status').val("1");

        $("#modal-create-product").modal();
    });

    $(".btn-edit-product").on("click", function () {
        var pdInfo = $(this).data("product");
        NProgress.start();
        axios.get("/api/product/detail/" + pdInfo).then(function(res){
            NProgress.done();
            if(res.data.success) {
                dataProduct.id = res.data.data.id;
                dataProduct.image = res.data.data.image;
                $("#input-product-name").val(res.data.data.name);
                $("#input-product-price").val(res.data.data.price);
                $("#input-product-quantity").val(res.data.data.quantity);
                $("#input-product-desc").val(res.data.data.description);
                $('#preview-product-img').attr('src', dataProduct.image);
                $('#product-category').val(res.data.data.category);
                $('#product-status').val(res.data.data.productStatus);
                $("#modal-create-product").modal();
            }
        }, function(err){
            NProgress.done();
        })
    });
    $(".btn-save-product").on("click", function () {
        if($("#input-product-name").val() === "" || $("#input-product-desc").val() === "" || dataProduct.image === undefined) {
            swal(
                'Error',
                'You need to fill all values',
                'error'
            );
            return;
        }
        dataProduct.name = $('#input-product-name').val();
        dataProduct.price = $('#input-product-price').val();
        dataProduct.quantity = $('#input-product-quantity').val();
        dataProduct.description = $('#input-product-desc').val();
        dataProduct.category = $('#product-category').val();
        dataProduct.productStatus = $('#product-status').val();
        NProgress.start();

        var linkPost = "/api/product/create-product";
        if(dataProduct.id) {
            linkPost = "/api/product/update-product/" + dataProduct.id;
        }

        axios.post(linkPost, dataProduct).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Good job!',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
                });
            } else {
                swal(
                    'Error',
                    res.data.message,
                    'error'
                );
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Some error when saving product',
                'error'
            );
        })
    });
    $(".btn-delete-product").on("click", function () {
        var pdInfo = $(this).data("product");
        console.log(pdInfo);
        swal({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            type: 'warning',
            showCancelButton: true
        }).then(function(result) {
            if (result.value) {
                NProgress.start();
                axios.get("/api/product/delete-product/"+pdInfo).then(function(res){
                    NProgress.done();
                    if(res.data.success) {
                        swal(
                            'Good job!',
                            res.data.message,
                            'success'
                        ).then(function() {
                            location.reload();
                        });
                    } else {
                        swal(
                            'Error',
                            res.data.message,
                            'error'
                        );
                    }
                }, function(err){
                    NProgress.done();
                    swal(
                        'Error',
                        'Some error when saving product',
                        'error'
                    );
                })
            }
        })
    });

    $("#btn-new-category").on("click", function () {
        dataCategory = {};
        $('#input-category-name').val("");
        $('#input-category-desc').val("");

        $("#modal-create-category").modal();
    });
    $(".btn-edit-category").on("click", function () {
        var cdInfo = $(this).data("category");
        NProgress.start();
        axios.get("/api/category/detail/" + cdInfo).then(function(res){
            NProgress.done();
            if(res.data.success) {
                dataCategory.id = res.data.data.id;
                $("#input-category-name").val(res.data.data.name);
                $("#input-category-desc").val(res.data.data.description);

                $("#modal-create-category").modal();
            }
        }, function(err){
            NProgress.done();
        })
    });
    $(".btn-save-category").on("click", function () {
        if($("#input-category-name").val() === "" || $("#input-category-desc").val() === "" ) {
            swal(
                'Error',
                'You need to fill all values',
                'error'
            );
            return;
        }
        dataCategory.name = $('#input-category-name').val();
        dataCategory.description = $('#input-category-desc').val();
        NProgress.start();

        var linkPost = "/api/category/create-category";
        if(dataCategory.id) {
            linkPost = "/api/category/update-category/" + dataCategory.id;
        }
        axios.post(linkPost, dataCategory).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Good job!',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
                });
            } else {
                swal(
                    'Error',
                    res.data.message,
                    'error'
                );
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Some error when saving category',
                'error'
            );
        })
    });
    $(".btn-delete-category").on("click", function () {
        var cdInfo = $(this).data("category");
        swal({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            type: 'warning',
            showCancelButton: true
        }).then(function(result) {
            if (result.value) {
                NProgress.start();
                axios.post("/api/category/delete-category", {
                    categoryId: cdInfo
                }).then(function(res){
                    NProgress.done();
                    if(res.data.success) {
                        swal(
                            'Good job!',
                            res.data.message,
                            'success'
                        ).then(function() {
                            location.reload();
                        });
                    } else {
                        swal(
                            'Error',
                            res.data.message,
                            'error'
                        );
                    }
                }, function(err){
                    NProgress.done();
                    swal(
                        'Error',
                        'Some error when saving product',
                        'error'
                    );
                })
            }
        })
    });

});