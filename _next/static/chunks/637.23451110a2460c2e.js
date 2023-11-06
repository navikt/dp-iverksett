"use strict";(self.webpackChunk_N_E=self.webpackChunk_N_E||[]).push([[637],{2637:function(e,t,l){l.d(t,{diagram:function(){return r}});var a=l(8144),i=l(9868),n=l(7928),o=l(4981),s=l(1471);l(4548),l(1417),l(3571),l(5855),l(152);let sanitizeText=e=>o.e.sanitizeText(e,(0,o.c)()),d={dividerMargin:10,padding:5,textHeight:10,curve:void 0},addNamespaces=function(e,t,l,a){let i=Object.keys(e);o.l.info("keys:",i),o.l.info(e),i.forEach(function(i){var n,s;let d=e[i],r={shape:"rect",id:d.id,domId:d.domId,labelText:sanitizeText(d.id),labelStyle:"",style:"fill: none; stroke: black",padding:(null==(n=(0,o.c)().flowchart)?void 0:n.padding)??(null==(s=(0,o.c)().class)?void 0:s.padding)};t.setNode(d.id,r),addClasses(d.classes,t,l,a,d.id),o.l.info("setNode",r)})},addClasses=function(e,t,l,a,i){let n=Object.keys(e);o.l.info("keys:",n),o.l.info(e),n.filter(t=>e[t].parent==i).forEach(function(l){var n,s;let d=e[l],r=d.cssClasses.join(" "),c={labelStyle:"",style:""},p=d.label??d.id,b={labelStyle:c.labelStyle,shape:"class_box",labelText:sanitizeText(p),classData:d,rx:0,ry:0,class:r,style:c.style,id:d.id,domId:d.domId,tooltip:a.db.getTooltip(d.id,i)||"",haveCallback:d.haveCallback,link:d.link,width:"group"===d.type?500:void 0,type:d.type,padding:(null==(n=(0,o.c)().flowchart)?void 0:n.padding)??(null==(s=(0,o.c)().class)?void 0:s.padding)};t.setNode(d.id,b),i&&t.setParent(d.id,i),o.l.info("setNode",b)})},addNotes=function(e,t,l,a){o.l.info(e),e.forEach(function(e,n){var s,r;let c={labelStyle:"",style:""},p=e.text,b={labelStyle:c.labelStyle,shape:"note",labelText:sanitizeText(p),noteData:e,rx:0,ry:0,class:"",style:c.style,id:e.id,domId:e.id,tooltip:"",type:"note",padding:(null==(s=(0,o.c)().flowchart)?void 0:s.padding)??(null==(r=(0,o.c)().class)?void 0:r.padding)};if(t.setNode(e.id,b),o.l.info("setNode",b),!e.class||!(e.class in a))return;let y=l+n,f={id:`edgeNote${y}`,classes:"relation",pattern:"dotted",arrowhead:"none",startLabelRight:"",endLabelLeft:"",arrowTypeStart:"none",arrowTypeEnd:"none",style:"fill:none",labelStyle:"",curve:(0,o.n)(d.curve,i.c_6)};t.setEdge(e.id,e.class,f,y)})},addRelations=function(e,t){let l=(0,o.c)().flowchart,a=0;e.forEach(function(e){var n;a++;let s={classes:"relation",pattern:1==e.relation.lineType?"dashed":"solid",id:"id"+a,arrowhead:"arrow_open"===e.type?"none":"normal",startLabelRight:"none"===e.relationTitle1?"":e.relationTitle1,endLabelLeft:"none"===e.relationTitle2?"":e.relationTitle2,arrowTypeStart:getArrowMarker(e.relation.type1),arrowTypeEnd:getArrowMarker(e.relation.type2),style:"fill:none",labelStyle:"",curve:(0,o.n)(null==l?void 0:l.curve,i.c_6)};if(o.l.info(s,e),void 0!==e.style){let t=(0,o.k)(e.style);s.style=t.style,s.labelStyle=t.labelStyle}e.text=e.title,void 0===e.text?void 0!==e.style&&(s.arrowheadStyle="fill: #333"):(s.arrowheadStyle="fill: #333",s.labelpos="c",(null==(n=(0,o.c)().flowchart)?void 0:n.htmlLabels)??(0,o.c)().htmlLabels?(s.labelType="html",s.label='<span class="edgeLabel">'+e.text+"</span>"):(s.labelType="text",s.label=e.text.replace(o.e.lineBreakRegex,"\n"),void 0===e.style&&(s.style=s.style||"stroke: #333; stroke-width: 1.5px;fill:none"),s.labelStyle=s.labelStyle.replace("color:","fill:"))),t.setEdge(e.id1,e.id2,s,a)})},draw=async function(e,t,l,a){let d;o.l.info("Drawing class - ",t);let r=(0,o.c)().flowchart??(0,o.c)().class,c=(0,o.c)().securityLevel;o.l.info("config:",r);let p=(null==r?void 0:r.nodeSpacing)??50,b=(null==r?void 0:r.rankSpacing)??50,y=new n.k({multigraph:!0,compound:!0}).setGraph({rankdir:a.db.getDirection(),nodesep:p,ranksep:b,marginx:8,marginy:8}).setDefaultEdgeLabel(function(){return{}}),f=a.db.getNamespaces(),u=a.db.getClasses(),g=a.db.getRelations(),h=a.db.getNotes();o.l.info(g),addNamespaces(f,y,t,a),addClasses(u,y,t,a),addRelations(g,y),addNotes(h,y,g.length+1,u),"sandbox"===c&&(d=(0,i.Ys)("#i"+t));let w="sandbox"===c?(0,i.Ys)(d.nodes()[0].contentDocument.body):(0,i.Ys)("body"),v=w.select(`[id="${t}"]`),k=w.select("#"+t+" g");if(await (0,s.r)(k,y,["aggregation","extension","composition","dependency","lollipop"],"classDiagram",t),o.u.insertTitle(v,"classTitleText",(null==r?void 0:r.titleTopMargin)??5,a.db.getDiagramTitle()),(0,o.o)(y,v,null==r?void 0:r.diagramPadding,null==r?void 0:r.useMaxWidth),!(null==r?void 0:r.htmlLabels)){let e="sandbox"===c?d.nodes()[0].contentDocument:document,l=e.querySelectorAll('[id="'+t+'"] .edgeLabel .label');for(let t of l){let l=t.getBBox(),a=e.createElementNS("http://www.w3.org/2000/svg","rect");a.setAttribute("rx",0),a.setAttribute("ry",0),a.setAttribute("width",l.width),a.setAttribute("height",l.height),t.insertBefore(a,t.firstChild)}}};function getArrowMarker(e){let t;switch(e){case 0:t="aggregation";break;case 1:t="extension";break;case 2:t="composition";break;case 3:t="dependency";break;case 4:t="lollipop";break;default:t="none"}return t}let r={parser:a.p,db:a.d,renderer:{setConf:function(e){d={...d,...e}},draw},styles:a.s,init:e=>{e.class||(e.class={}),e.class.arrowMarkerAbsolute=e.arrowMarkerAbsolute,a.d.clear()}}}}]);