
/*
 * 电子面单
 */






/**
 * print_type:决定类型,BIG_是普通大面单，ELEC是电子小面单
 */
function print_express(order_no,seq,logistics_code,logistics_vehicle,print_type,wh_id,userid){
	

	
	
	
	
	$.getJSON("../express/get_express_bills.action", {"logistics_code":logistics_code,"print_type":print_type
	}, function(result) {
		
		var field=result.msg;
			
		
			
			if (field.success){
				//得到数量
				var bills=field.o;
				//$('#bill_count').text(n);
				if (bills.length==1){
					//alert (bills[0].id.logisticsBillCode);
					print_logistics(order_no,seq,logistics_code,logistics_vehicle,print_type,bills[0].id.logisticsBillCode,wh_id,userid);
				}else{
					$.getJSON("../express/get_express_bills.action", {"logistics_code":logistics_code,"print_type":print_type
					}, function(result2) {
						
						var html="<div class=\'logistics_bill\'>";
							var field2=result2.msg;
						
							
							
							var bills=field2.o;
							for (i2=0;i2<bills.length;i2++){
								var bill=bills[i2];
								html=html+'<span  style=\'float:left;\'><input type=\'hidden\' id=\'type'+i2+'\' value=\''+bill.printType+'\'/><input type=\'hidden\' id=\'h'+i2+'\' value=\''+bill.id.logisticsBillCode+'\'/><dl id=\'s'+i2+'\'><dt><img width=\'200px\' src=\'../img/logistics_bill/'+bill.id.logisticsCode+bill.id.logisticsBillCode+'.jpg\'/></dt>';

								html=html+'<dd>';
								html=html+bill.logisticsBillDesc;
								
								html=html+'</dd>';
									
									
								
								html=html+'</dl></span>';
							}
							
							html=html+'<div class=\'clear\'></div>';
							html=html+'</div>';
							//alert(html);
							$('#logistics_bills').html(html);
							
							for (i3=0;i3<bills.length;i3++){
								var bill=bills[i3];
								//alert(bill.id.logisticsBillCode);
								
								$("#s"+i3).bind("click",function(){
									var imgid = $(this).attr("id");
							         //alert(imgid );
							        var seq=imgid.substring(1,imgid.length);
							        //alert(seq);
							        //alert($('#type'+seq).val());
							        print_logistics(order_no,seq,logistics_code,logistics_vehicle,$('#type'+seq).val(),$('#h'+seq).val(),wh_id,userid)
							       
								});
							}
							
							
							
							dialog_init('print_logistics_bill');
							//$("#print_logistics_bill").panel("move",{top:$(document).scrollTop() + ($(window).height()-400) * 0.5}); 
							$('#print_logistics_bill').dialog('open');
						
					});
						
						

					
					
					
				}

				
				}
			else{
				alter("错误"+field.body);
			}
			
		
	
			
		
	});
	
	
	
	
	
}
function print_logistics(order_no,seq,logistics_code,logistics_vehicle,logistics_type,bill,wh_id,userid){
	//alert(wh_id);
	//alert(userid);
	//alert(logistics_type);
	var url="#";
	if (order_no!=''){
		//打印订单的快递单
		//根据快递公司和类型不同，分别处理
		if (logistics_code=='500528000A'&&logistics_type=='ELEC'){
			
			
			if (seq=='0000'){
				//得到快递单号
				
				$.getJSON("../express/sf/get_sf_elec_bill.action", {
					order_no:order_no,
					logistics_vehicle:logistics_vehicle,
					wh_id:wh_id,
					userid:userid
						
					}, function(result) {

						var field=result.msg;

							if (field.success) {
								//window.location.href="contract_list_init.action";
								var dm=field.o;
								
								
								$('#new_deliver_express_no').val(dm.express_no);
								$('#new_deliver_seq').val(dm.seq);
								//移动焦点
								$("#pickup_barcode").focus();
								
								
								url="../print/sf_elec_logistics_print.action?order_no="+order_no + "&seq=" + dm.seq+"&logistics="+logistics_code+"&bill="+bill+"&wh_id="+wh_id+"&userid="+userid;
								//alert(url);
								window.open(url);

							}
							

						

					});
			}
			else{
				//已经存在seq发货单
				
				
				url="../print/sf_elec_logistics_print.action?order_no="+order_no + "&seq=" + seq+"&logistics="+logistics_code+"&bill="+bill+"&wh_id="+wh_id+"&userid="+userid;
				//alert(url);
				window.open(url);
			}
			
		}
		else{
			url="../print/order_logistics_print.action?order_no="+order_no + "&logistics=" + logistics_code+"&logistics_type="+logistics_type+"&bill="+bill;
			//alert(url);
			window.open(url);
		}
		
		
		
		
	}
	/*
	if (sender!=''){
		//alert(sender_addr);
		
		url="../print/custom_logistics_print.action?order=''&logistics="+logistics_code+"&sender="+sender+"&sender_com="+sender_com+"&sender_addr="+sender_addr+"&sender_tel="+sender_tel+"&receiver="+receiver+"&receiver_tel="+receiver_tel+"&receiver_addr="+receiver_addr+"&something="+something+"&bill="+bill;
		
		
		window.open(url);	
	}
	*/
	
	
}