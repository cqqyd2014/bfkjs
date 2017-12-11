//基础参数
var dialog_width="700px";
var dialog_height="400px";
var dialog_width_mid="500px";
var dialog_height_mid="300px";
var dialog_width_little="300px";
var dialog_height_little="200px";
//窗口初始化

function dialog_init(o){
	$('#'+o).window('resize',{width:dialog_width,height:dialog_height});
	$('#'+o).dialog('close');
	$('#'+o).window('center'); 
}
function dialog_init_mid(o){
	$('#'+o).window('resize',{width:dialog_width_mid,height:dialog_height_mid});
	$('#'+o).dialog('close');
	$('#'+o).window('center'); 
}
function dialog_init_little(o){
	$('#'+o).window('resize',{width:dialog_width_little,height:dialog_height_little});
	$('#'+o).dialog('close');
	$('#'+o).window('center'); 
	//$('#'+o).panel("move",{top : $(document).scrollTop()+ ($(window).height() - dialog_height_little) * 0.5	});
}

//ajax未认证处理，中心框架用

function ajax_authority(field){
	if (field==null){
		return;
	}
	if (!field.auth_success){
		window.parent.parent.frames.location.href="/Bfkjs";
	}
	
}
//top框架使用
function ajax_authority2(field){
	if (field==null){
		return;
	}
	if (!field.auth_success){
		window.parent.frames.location.href="/Bfkjs";
	}
	
}
//无框架使用
function ajax_authority2(field){
	if (field==null){
		return;
	}
	if (!field.auth_success){
		window.location.href="/Bfkjs";
	}
	
}

//供使用者调用  
function trim(s){  
    return trimRight(trimLeft(s));  
}  
//去掉左边的空白  
function trimLeft(s){  
    if(s == null) {  
        return "";  
    }  
    var whitespace = new String(" \t\n\r");  
    var str = new String(s);  
    if (whitespace.indexOf(str.charAt(0)) != -1) {  
        var j=0, i = str.length;  
        while (j < i && whitespace.indexOf(str.charAt(j)) != -1){  
            j++;  
        }  
        str = str.substring(j, i);  
    }  
    return str;  
}  

//去掉右边的空白 www.2cto.com   
function trimRight(s){  
    if(s == null) return "";  
    var whitespace = new String(" \t\n\r");  
    var str = new String(s);  
    if (whitespace.indexOf(str.charAt(str.length-1)) != -1){  
        var i = str.length - 1;  
        while (i >= 0 && whitespace.indexOf(str.charAt(i)) != -1){  
           i--;  
        }  
        str = str.substring(0, i+1);  
    }  
    return str;  
}         


/**
 * 
 */
function set_default(code, value) {


	$.ajax({
		type : "POST",
		url : "../system/set_user_default.action",
		data : {
			"par_code" : code,
			"par_value" : value
		},
		success : function(data) {
			
			var field=data.msg;
			ajax_authority(field);
			if (field.success){
				$.messager.alert('提示','设置默认参数成功','info');
				
			
			
			} else {
				$.messager.alert('错误','设置默认参数失败','error');
			}
		}
	});
}
function timeStamp2String(time){
    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
}
function date2String(in_date){
	var in_time=in_date.time;
    return timeStamp2String(in_time);
}


function clearDataTable(t_id){
	var o=JSON.parse('[]');
	var n = o.length;

	$("#"+t_id).datagrid('loadData', {
		total : n,
		rows : o
	})
}
function setDisabled(target, disabled) {
    var state = $.data(target, "linkbutton");
    if (disabled) {
        state.options.disabled = true;
        var href = $(target).attr("href");
        if (href) {
            state.href = href;
            $(target).attr("href", "javascript:void(0)");
        }
        if (target.onclick) {
            state.onclick = target.onclick;
            target.onclick = null;
        }
        //事件处理
    var events = $(target).data("events");
    if(events){
        var clicks = events.click;//暂时只处理click事件
        state.events = state.events||[];
        $.extend(state.events, clicks);
        $(target).unbind("click");
    }
        $(target).addClass("l-btn-disabled");
    } else {
        state.options.disabled = false;
        if (state.href) {
            $(target).attr("href", state.href);
        }
        if (state.onclick) {
            target.onclick = state.onclick;
        }
        if (state.events) {
        for ( var i=0;i<state.events.length;i++){
        $(target).bind(state.events[i].type,state.events[i].handler);
            }
        }
        $(target).removeClass("l-btn-disabled");
    }
 // 验证中文名称
    function isChinaName(name) {
     var pattern = /^[\u4E00-\u9FA5]{1,6}$/;
     return pattern.test(name);
    }
     
    // 验证手机号
    function isPhoneNo(phone) { 
     var pattern = /^1[34578]\d{9}$/; 
     return pattern.test(phone); 
    }
     
    // 验证身份证 
    function isCardNo(card) { 
     var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
     return pattern.test(card); 
    } 
}

