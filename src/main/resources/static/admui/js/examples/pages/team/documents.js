(function (document, window, $) {
    'use strict';

    window.Content = {
        affixHandle: function () {
            $('#articleAffix').affix({
                offset: {
                    top: 210
                }
            });
        },
        scrollHandle: function () {
            $('body').scrollspy({
                target: '#articleAffix'
            });
        },
        run: function () {
            this.scrollHandle();
            this.affixHandle();

        }
    };

})(document, window, jQuery);
