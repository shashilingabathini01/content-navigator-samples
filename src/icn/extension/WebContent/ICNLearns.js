require(["dojo/_base/lang","ecm/model/Desktop","dojo/aspect"], function(lang,Desktop,aspect) {

    lang.setObject("learnDocumentAction",function(repository,items) {
        alert('action is called');
    });

    aspect.after(Desktop,"onLogin",lang.hitch(this,function(repository) {
        console.log('onLogin completed');
        ecm.mode.desktop.userLoginName;
    }), true);
});

