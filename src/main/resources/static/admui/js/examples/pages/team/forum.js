(function (document, window) {
    'use strict';

    window.Content = App.extend({
        run: function (next) {
            $('.markdown-edit').markdown({
                language: 'zh',
                iconlibrary: 'fa'
            });

            next();
        }
    });
    
})(document, window);
