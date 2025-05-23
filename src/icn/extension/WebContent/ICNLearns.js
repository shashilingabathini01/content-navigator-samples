require(["dojo/_base/lang","ecm/model/Desktop","dojo/aspect","ecm/widget/dialog/BaseDialog"], function(lang,Desktop,aspect,BaseDialog) {

    lang.setObject("learnDocumentAction",function(repository,items) {
        alert('action is called');
        var grid = "";
        var dialog = new BaseDialog({
            contentString : '<html> <div> data is '+ data ' </div>'
        });
        dialog.show();
    });
});

