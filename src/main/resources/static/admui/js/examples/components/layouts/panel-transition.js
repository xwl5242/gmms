(function (document, window, $) {
    'use strict';

    var $example = $('#exampleTransition'),
        $pageContent = $('#admui-pageContent');

    $pageContent.on('click.panel.transition', '[data-type]', function () {
        var type = $(this).data('type');

        $example.data('animateList').run(type);
    });

    $pageContent.on('close.uikit.panel', '[class*=blocks-] > li > .panel', function () {
        $(this).parent().hide();
    });

})(document, window, jQuery);