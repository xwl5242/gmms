(function (window, document, $) {
    "use strict";

    $('#dataTableExample').DataTable($.po('dataTable', {
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": "http://demo.admui.com/employee/all",
            "dataType": "jsonp"
        }
    }));
})(window, document, jQuery);

