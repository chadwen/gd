//CommonService.js

import $ from '../unit/JQueryVendor';
//import layer from '../unit/JqLayer/layer';
//import layer from "../unit/JqLayer/layer/layer";
class CommonService{
    constructor(options) {
        this.options = options || {};
        window.layer = layer;
    }

    OpenModalLayer() {
        let self = this;
        //参数默认值
        self.options.header = self.options.header || '';
        self.options.content = self.options.content || '';
        self.options.footer = self.options.footer || '';
        self.options.endFunction = self.options.endFunction || function () { };
        layer.open({
            id: 1,
            //offset: '20%',//坐标
            anim: 0,//动画
            title: false,//标题
            type: 1,//页面层
            closeBtn: 0,//关闭按钮
            area: '600px',//宽高
            skin: 'layui-layer-rim',//风格
            shade: 0.6,//遮罩层透明度
            shadeClose: true, //点击遮罩关闭
            resize: false,//拉伸
            move: false,//拖动
            content: '<div class="modal-content" style="border-radius:0px;">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" onclick="parent.layer.closeAll();">&times;</button>' +
            '<h4 class="modal-title topTitle">' + self.options.header + '</h4>' +
            '</div>' +
            '<div class="modal-body">' + self.options.content +
            '</div>' +
            '<div class="modal-footer">' + self.options.footer +
            '</div>' +
            '</div>',
            end: self.options.endFunction
        });
    }

    OpenParentModalLayer() {
        let self = this;
        //参数默认值
        self.options.header = self.options.header || '';
        self.options.content = self.options.content || '';
        self.options.footer = self.options.footer || '';
        self.options.endFunction = self.options.endFunction || function () { };
        layer.open({
            id: 1,
            offset: ['10%', '33%'],//坐标
            anim: 3,//动画
            title: false,//标题
            type: 1,//页面层
            closeBtn: 0,//关闭按钮
            area: '33%',//宽高
            skin: 'layui-layer-rim',//风格
            shade: 0.6,//遮罩层透明度
            shadeClose: true, //点击遮罩关闭
            resize: false,//拉伸
            move: false,//拖动
            content: '<div class="modal-content" style="border-radius:0px;">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" onclick="parent.layer.closeAll();">&times;</button>' +
            '<h4 class="modal-title topTitle">' + self.options.header + '</h4>' +
            '</div>' +
            '<div class="modal-body">' + self.options.content +
            '</div>' +
            '<div class="modal-footer">' + self.options.footer +
            '</div>' +
            '</div>',
            end: self.options.endFunction
        });
    }
    OpenParentModalLayerForDocIndexCtr() {//与 OpenParentModalLayer 只有一个属性值不同：shadeClose: false, 禁止点击空白处关闭
        let self = this;
        //参数默认值
        self.options.header = self.options.header || '';
        self.options.content = self.options.content || '';
        self.options.footer = self.options.footer || '';
        self.options.endFunction = self.options.endFunction || function () { };
        layer.open({
            id: 1,
            //offset: '20%',//坐标
            anim: 0,//动画
            title: false,//标题
            type: 1,//页面层
            closeBtn: 0,//关闭按钮
            area: '600px',//宽高
            skin: 'layui-layer-rim',//风格
            shade: 0.6,//遮罩层透明度
            shadeClose: false, //点击遮罩关闭
            resize: false,//拉伸
            move: false,//拖动
            content: '<div class="modal-content" style="border-radius:0px;">' +
            '<div class="modal-header">' +
            //'<button type="button" class="close" onclick="parent.layer.closeAll();">&times;</button>' +
            '<h4 class="modal-title topTitle">' + self.options.header + '</h4>' +
            '</div>' +
            '<div class="modal-body">' + self.options.content +
            '</div>' +
            '<div class="modal-footer">' + self.options.footer +
            '</div>' +
            '</div>',
            end: self.options.endFunction
        });
    }
    OpenSaveModalLayer() {
        let self = this;
        //参数默认值
        self.options.header = self.options.header || '';
        self.options.content = self.options.content || '';
        parent.layer.open({
            id: 1,
            //offset: '20%',
            anim: 0,//动画
            title: false,//标题
            type: 1,//页面层
            closeBtn: 0,//关闭按钮
            area: '600px',//宽高
            skin: 'layui-layer-rim',//风格
            shade: 0.6,//遮罩层透明度
            shadeClose: false, //点击遮罩关闭
            resize: false,//拉伸
            move: false,//拖动
            content: '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" onclick="parent.layer.closeAll();">&times;</button>' +
            '<h4 class="modal-title topTitle">' + self.options.header + '</h4>' +
            '</div>' +
            '<div class="modal-body">' + self.options.content +
            '</div>' +
            '<div class="modal-footer">' +
            '<button type="button" class="btn btn-default CloseLayer" onclick="parent.layer.closeAll();">关闭</button>' +
            '<button type="submit" class="btn btn-primary">保存</button>' +
            '</div>' +
            '</div>'
        });
        $('.btn.btn-primary', window.parent.document).click(function () {
            parent.layer.closeAll();
            self.options.Submit(this);
        });
    }

    OpenConfirmModalLayer() {
        let self = this;
        parent.layer.open({
            id: 1,
            anim: 0,//动画
            title: false,//标题
            type: 1,//页面层
            closeBtn: 0,//关闭按钮
            area: '600px',//宽高
            skin: 'layui-layer-rim',//风格
            shade: 0.6,//遮罩层透明度
            shadeClose: false, //点击遮罩关闭
            resize: false,//拉伸
            move: false,//拖动
            content: '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" onclick="parent.layer.closeAll();">&times;</button>' +
            '<h4 class="modal-title topTitle">' + self.options.header + '</h4>' +
            '</div>' +
            '<div class="modal-body">' + self.options.content +
            '</div>' +
            '<div class="modal-footer">' +
            '<button type="button" class="btn btn-default" onclick="parent.layer.closeAll();">否</button>' +
            '<button type="button" id="btnConfirm" class="btn btn-default">是</button>' +
            '</div>' +
            '</div>'
        })
        let confirmctr = $("#btnConfirm");
        if (confirmctr[0] == undefined) {
            confirmctr = $("#btnConfirm", window.parent.document);
        }
        confirmctr.bind('click', () => {
            if (self.options.Confirm != undefined)
                self.options.Confirm.call(this);
        });
    }
    Loading() {
        let self = this;
        self.index = parent.layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
    }

    CloseLoading(){
        let self = this;
        parent.layer.close(self.index);
    }

    //1 成功 2 失败/异常  3感叹号 4印章 5失望标签符
    AlertMessage(message, iconNum) {
        let self = this;
        self.options.iconNum = iconNum || 1;
        parent.layer.alert(message, {
            icon: self.options.iconNum,
            skin: 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
        });
    }

    PromptMsg(message, iconNum) {
        let self = this;
        self.options.iconNum = iconNum || 1;
        parent.layer.msg(message, {
            icon: self.options.iconNum,
            time: 5000 //3秒关闭（如果不配置，默认是3秒）
        }, function () {
            //提示窗关闭后do something
        });
    }

    CloseLayer() {
        parent.layer.closeAll();
    }
	
}
export default CommonService;