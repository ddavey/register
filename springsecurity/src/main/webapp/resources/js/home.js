var selected = null;

var eventIdCache = {};

$('#calendar').fullCalendar(
		{
			header: {
				left: 'prev,next today',
				center: 'title wfh',
				right: 'month,basicWeek'
			},
			defaultView: 'basicWeek',
			weekends:false,
			editable:true,
			selectable:true,
			eventLimit:1,
			viewRender : function( view, element ){
				getEventsForRange(view.activeRange.start._d.getTime(),view.activeRange.end._d.getTime(),function(events){
					$.each(events,function(index,event){
						if(!eventIdCache[event.id]){
							eventIdCache[event.id] = event.id;
							addEventToCalendar(event);
						}	
						
					});
					
				});
			},
			selectAllow:function(selectInfo){
				var allowed = (selectInfo.end-selectInfo.start)/(60000*60*24) == 1;
				
				return allowed;
			},
			select: function(start,end,jsEvent,view,resource){
				selected = start;
				
				var events = [];
				$('#calendar').fullCalendar( 'clientEvents',function(event,index,start){
						if(event.start._d.getTime() == selected._d.getTime()){
							events.push(event);
						}
					} );
				
				if(events.length==0){
					addWfh(start._d.getTime());
					
				}else{
					if(events[0].title=='WFH'){
						deleteWfh(events[0]);
						
					}
				}
					
			}
		});

function getEventsForRange(start,end,callback){
	$.post('events/list',{start:start,end:end,_csrf:_csrfToken},function(events){callback(events)});
}

function addEventToCalendar(event){
	eventData = {
			id: event.id,
			title: event.type.replace('_',''),
			start: new Date(event.date),
			end: new Date(event.date),
			allDay:true
		};
	
	$('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
}

function addWfh(date){
	$.post("events/wfh/add",{date:date,_csrf:_csrfToken},function(event){
		eventIdCache[event.id] = event.id;
		addEventToCalendar(event);
	});
}

function deleteWfh(event){
	$.post("events/wfh/delete",{id:event.id,_csrf:_csrfToken},function(removedFlag){
		$('#calendar').fullCalendar('removeEvents',event.id);
	});
	
		
		
}


