(function (document, window, $) {
    'use strict';

    window.Content = {
        run: function(){
            if ($('.list-group[data-plugin="nav-tabs"]').length) {
                $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
                    $(e.target).addClass('active').siblings().removeClass('active');
                });
            }
        }
    };
})(document, window, jQuery);