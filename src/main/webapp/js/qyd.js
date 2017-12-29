


//基础参数
var dialog_width = "700px";
var dialog_height = "400px";
var dialog_width_mid = "500px";
var dialog_height_mid = "300px";
var dialog_width_little = "300px";
var dialog_height_little = "200px";
// 窗口初始化

function dialog_init(o) {
	$('#' + o).window('resize', {
		width : dialog_width,
		height : dialog_height
	});
	$('#' + o).dialog('close');
	$('#' + o).window('center');
}
function dialog_init_mid(o) {
	$('#' + o).window('resize', {
		width : dialog_width_mid,
		height : dialog_height_mid
	});
	$('#' + o).dialog('close');
	$('#' + o).window('center');
}
function dialog_init_little(o) {
	$('#' + o).window('resize', {
		width : dialog_width_little,
		height : dialog_height_little
	});
	$('#' + o).dialog('close');
	$('#' + o).window('center');
	// $('#'+o).panel("move",{top : $(document).scrollTop()+ ($(window).height()
	// - dialog_height_little) * 0.5 });
}

function refresh_tab(currentTab) {
	var url = $(currentTab.panel('options')).attr('href');
	$('#tabs').tabs('update', {
		tab : currentTab,
		options : {
			href : url
		}
	});
	currentTab.panel('refresh');
}

// ajax未认证处理

function ajax_authority(field) {
	if (field == null) {
		return;
	}
	if (!field.auth_success) {
		window.top.location.href = "/finance";
	}

}

function easyui_textbox_focus(object){
	$('#'+object).next('span').find('input').focus();
}


function easyui_textbox_enter(object,method){
	$('#'+object).textbox('textbox').keydown(function (e) {
	    if (e.keyCode == 13) {
	    	method();
	    }
	});
	

}

function easyui_textbox_key(object,method){
	$('#'+object).textbox('textbox').keydown(function (e) {
		
	   
	    	method(e);
	   
	});
	

}



function ajax_start() {
	$(".fakeloader").fakeLoader({  
        timeToHide: 60000,  
        bgColor: "#A8A8A8",  
        spinner: "spinner2"  
    });  

}
function ajax_stop() {
	$(".fakeloader").fakeLoader({  
        timeToHide: 1,  
        bgColor: "#A8A8A8",  
        spinner: "spinner2"  
    });  
}
function easyui_textbox_tirm(o){
	var value=$('#'+o).textbox('getValue');
	value=$.trim(value);
	$('#'+o).textbox('setValue',value);
	return value;
}

Array.prototype.removeByValue = function(val) {
	  for(var i=0; i<this.length; i++) {
	    if(this[i] == val) {
	      this.splice(i, 1);
	      break;
	    }
	  }
	}

//
/**
 * 
 * 测试数组中的对象是否都为空，一般是check_string_allowed_blank_not_length之后的判断
 * 
 * @method allEmpty
 * 
 * @for 所属类名
 * 
 * @param array
 *            需要检查的字符串数组
 *
 * 
 * 
 * 
 * 
 * 
 * @return boolean 返回值说明
 */

function allEmpty(array){
	for (var i=0;i<array.length;i++){
		if ($('#'+array[i]).text()!=''){
			return false;
		}
	}
	return true;
	
}


/*
 * 测试，是否存在某一个为空
 * 
 */
function anyEmpty(array){
	for (var i=0;array.length;i++){
		if ($('#'+array[i]).text()==''){
			return true;
		}
	}
	return false;
	
}


/*
 * 
 * easyui 采用ajax加载出现对话框重复
 * 
 */
function easyui_ajax_div_clean(div_id){
	//console.log(div_id);
	var picture_divs = $('*[id='+div_id+']')
	var picture_divs_size=picture_divs.size();
	//console.log(picture_divs_size);
	
	if (picture_divs_size>=2){
		var flag=0;
		$('*[id='+div_id+']').each(function(){
		    
		    if (flag>0){
		    	$(this).dialog('destroy');
		    }
		    flag++;
		  });
		
		
		/*
		console.log($('#'+div_id));
		for (var i=0;i<picture_divs_size;i++){
			//$('#'+div_id).dialog('destroy');
			//console.log($('#'+div_id));
			//picture_divs.eq(0).dialog('destroy');
			}
			*/
		/*
		console.log("del ok");
		picture_divs = $('*[id='+div_id+']')
		picture_divs_size=picture_divs.size();
		console.log(picture_divs.size());
		*/
		}
}

// 
/**
 * 
 * 检查字符串，宽松模式可以为空，但是不能超过长度,
 * 
 * @method check_string_allowed_blank_not_length
 * 
 * @for 所属类名
 * 
 * @param check_string
 *            需要检查的字符串
 * @param message_string
 *            提示信息的字符串
 * 
 * 
 * 
 * 
 * 
 * @return {返回值类型} 返回值说明
 */
function check_string_allowed_blank_not_length(check_string, len) {
	var value = trim($('#' + check_string).val());
	if (value == '') {
		$('#' + check_string + '_msg').html(
				'<font color="red">' + $('#' + check_string + '_title').text()
						+ '不能为空' + '</font>');
		return true;
	}
	if (value.length >= len) {
		$('#' + check_string + '_msg').html(
				'<font color="red">' + $('#' + check_string + '_title').text()
						+ '长度不能超过' + len + '个字符' + '</font>');
		return false;
	}
	$('#' + check_string + '_msg').html('');
	return true;
}
/**
 * 
 * 检查数字需要大于一个值，比如0，宽松模式可以为0,提示警告，总是返回true,
 * 
 * @method check_string_allowed_blank_not_length
 * 
 * @for 所属类名
 * 
 * @param check_string
 *            需要检查的字符串
 * @param message_string
 *            提示信息的字符串
 * 
 * 
 * 
 * 
 * 
 * @return {返回值类型} 返回值说明
 */
function check_num_allowed_bigger_tip(check_num, value) {
	if ($('#' + check_num).val() < value) {
		$('#' + check_num + '_msg').html(
				'<font color="red">' + $('#' + check_num + '_title').text()
						+ '不能小于1' + '</font>');
	} else {
		$('#' + check_num + '_msg').html('');
	}

	return true;
}

// 供使用者调用
function trim(s) {
	return trimRight(trimLeft(s));
}
// 去掉左边的空白
function trimLeft(s) {
	if (s == null) {
		return "";
	}
	var whitespace = new String(" \t\n\r");
	var str = new String(s);
	if (whitespace.indexOf(str.charAt(0)) != -1) {
		var j = 0, i = str.length;
		while (j < i && whitespace.indexOf(str.charAt(j)) != -1) {
			j++;
		}
		str = str.substring(j, i);
	}
	return str;
}

// 去掉右边的空白 www.2cto.com
function trimRight(s) {
	if (s == null)
		return "";
	var whitespace = new String(" \t\n\r");
	var str = new String(s);
	if (whitespace.indexOf(str.charAt(str.length - 1)) != -1) {
		var i = str.length - 1;
		while (i >= 0 && whitespace.indexOf(str.charAt(i)) != -1) {
			i--;
		}
		str = str.substring(0, i + 1);
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

			var field = data.msg;
			ajax_authority(field);
			if (field.success) {
				$.messager.alert('提示', '设置默认参数成功', 'info');

			} else {
				$.messager.alert('错误', '设置默认参数失败', 'error');
			}
		}
	});
}
function timeStamp2String(time) {
	var datetime = new Date();
	datetime.setTime(time);
	var year = datetime.getFullYear();
	var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1)
			: datetime.getMonth() + 1;
	var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime
			.getDate();
	var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime
			.getHours();
	var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes()
			: datetime.getMinutes();
	var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds()
			: datetime.getSeconds();
	return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":"
			+ second;
}
function date2String(in_date) {
	var in_time = in_date.time;
	return timeStamp2String(in_time);
}

function clearDataTable(t_id) {
	var o = JSON.parse('[]');
	var n = o.length;

	$("#" + t_id).datagrid('loadData', {
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
		// 事件处理
		var events = $(target).data("events");
		if (events) {
			var clicks = events.click;// 暂时只处理click事件
			state.events = state.events || [];
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
			for (var i = 0; i < state.events.length; i++) {
				$(target).bind(state.events[i].type, state.events[i].handler);
			}
		}
		$(target).removeClass("l-btn-disabled");
	}
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
	jQuery.prototype.serializeObject=function(){  
	    var a,o,h,i,e;  
	    a=this.serializeArray();  
	    o={};  
	    h=o.hasOwnProperty;  
	    for(i=0;i<a.length;i++){  
	        e=a[i];  
	        if(!h.call(o,e.name)){  
	            o[e.name]=e.value;  
	        }  
	    }  
	    return o;  
	};  
	
	
	
