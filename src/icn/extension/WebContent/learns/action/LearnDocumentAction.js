define(["dojo/_base/declare","ecm/model/Action","dojo/_base/lang","ecm/widget/dialog/MessageDialog","dojo/_base/array"],
 function(declare,Action,lang,MessageDialog,array) {
    return declare("learns.action.LearnDocumentAction",[Action],{

        isVisible: function(repository, items, repositoryTypes, teamspace) {
            return true;
        },
        isEnabled: function(repository, listType, items, teamspace, resultSet) {
            return true;
        },
        performAction: function(repository, itemList, callback, teamspace, resultSet, parameterMap) {
            var document = itemList[0]; // this is document
            var string = "";
            document.retrieveAttributes(lang.hitch(this,function(attributes) {
                array.forEach(attributes, lang.hitch(this,function(attribute) {
                    string += attribute.name +  " ";
                }
                this.show(string);
            ));
        },
        show : function(attributes) {
            var message = new MessageDialog({
                text : attributes
            });
            message.show();
        },
        showInformation: function(data) {

        }
    });
});