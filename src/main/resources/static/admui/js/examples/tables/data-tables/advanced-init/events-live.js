(function (window, document, $) {
    "use strict";

    var table = $('#dataTableExample').DataTable($.po('dataTable'));
    $('#admui-pageContent').on('click', '#dataTableExample tbody tr', function () {
        var data = table.row(this).data();
        toastr.info('您单击了"' + data[0] + '"的行');
    });
})(window, document, jQuery);

