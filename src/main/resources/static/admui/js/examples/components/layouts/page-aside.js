(function (document, window) {
    'use strict';

    window.Content = App.extend({
        run: function(next){
            next();
        }
    });
})(document, window);
