"use strict";(self.webpackChunk_N_E=self.webpackChunk_N_E||[]).push([[936],{9354:function(e,t,r){r.d(t,{c:function(){return write}});var n=r(9360),i=r(8451),d=r(3836);function write(e){var t,r={options:{directed:e.isDirected(),multigraph:e.isMultigraph(),compound:e.isCompound()},nodes:writeNodes(e),edges:writeEdges(e)};return n.Z(e.graph())||(r.value=(t=e.graph(),(0,i.Z)(t,4))),r}function writeNodes(e){return d.Z(e.nodes(),function(t){var r=e.node(t),i=e.parent(t),d={v:t};return n.Z(r)||(d.value=r),n.Z(i)||(d.parent=i),d})}function writeEdges(e){return d.Z(e.edges(),function(t){var r=e.edge(t),i={v:t.v,w:t.w};return n.Z(t.name)||(i.name=t.name),n.Z(r)||(i.value=r),i})}r(2544)},7936:function(e,t,r){r.d(t,{r:function(){return render}});var n=r(1644),i=r(9354),d=r(5269),l=r(5478),a=r(5625),s=r(171),o=r(4218);let c={},h={},g={},clear$1=()=>{h={},g={},c={}},isDescendant=(e,t)=>(l.l.trace("In isDecendant",t," ",e," = ",h[t].includes(e)),!!h[t].includes(e)),edgeInCluster=(e,t)=>(l.l.info("Decendants of ",t," is ",h[t]),l.l.info("Edge is ",e),e.v!==t&&e.w!==t&&(h[t]?h[t].includes(e.v)||isDescendant(e.v,t)||isDescendant(e.w,t)||h[t].includes(e.w):(l.l.debug("Tilt, ",t,",not in decendants"),!1))),copy=(e,t,r,n)=>{l.l.warn("Copying children of ",e,"root",n,"data",t.node(e),n);let i=t.children(e)||[];e!==n&&i.push(e),l.l.warn("Copying (nodes) clusterId",e,"nodes",i),i.forEach(i=>{if(t.children(i).length>0)copy(i,t,r,n);else{let d=t.node(i);l.l.info("cp ",i," to ",n," with parent ",e),r.setNode(i,d),n!==t.parent(i)&&(l.l.warn("Setting parent",i,t.parent(i)),r.setParent(i,t.parent(i))),e!==n&&i!==e?(l.l.debug("Setting parent",i,e),r.setParent(i,e)):(l.l.info("In copy ",e,"root",n,"data",t.node(e),n),l.l.debug("Not Setting parent for node=",i,"cluster!==rootId",e!==n,"node!==clusterId",i!==e));let a=t.edges(i);l.l.debug("Copying Edges",a),a.forEach(i=>{l.l.info("Edge",i);let d=t.edge(i.v,i.w,i.name);l.l.info("Edge data",d,n);try{edgeInCluster(i,n)?(l.l.info("Copying as ",i.v,i.w,d,i.name),r.setEdge(i.v,i.w,d,i.name),l.l.info("newGraph edges ",r.edges(),r.edge(r.edges()[0]))):l.l.info("Skipping copy of edge ",i.v,"-->",i.w," rootId: ",n," clusterId:",e)}catch(e){l.l.error(e)}})}l.l.debug("Removing node",i),t.removeNode(i)})},extractDescendants=(e,t)=>{let r=t.children(e),n=[...r];for(let i of r)g[i]=e,n=[...n,...extractDescendants(i,t)];return n},findNonClusterChild=(e,t)=>{l.l.trace("Searching",e);let r=t.children(e);if(l.l.trace("Searching children of id ",e,r),r.length<1)return l.l.trace("This is a valid node",e),e;for(let n of r){let r=findNonClusterChild(n,t);if(r)return l.l.trace("Found replacement for",e," => ",r),r}},getAnchorId=e=>c[e]&&c[e].externalConnections&&c[e]?c[e].id:e,adjustClustersAndEdges=(e,t)=>{if(!e||t>10){l.l.debug("Opting out, no graph ");return}l.l.debug("Opting in, graph "),e.nodes().forEach(function(t){let r=e.children(t);r.length>0&&(l.l.warn("Cluster identified",t," Replacement id in edges: ",findNonClusterChild(t,e)),h[t]=extractDescendants(t,e),c[t]={id:findNonClusterChild(t,e),clusterData:e.node(t)})}),e.nodes().forEach(function(t){let r=e.children(t),n=e.edges();r.length>0?(l.l.debug("Cluster identified",t,h),n.forEach(e=>{if(e.v!==t&&e.w!==t){let r=isDescendant(e.v,t),n=isDescendant(e.w,t);r^n&&(l.l.warn("Edge: ",e," leaves cluster ",t),l.l.warn("Decendants of XXX ",t,": ",h[t]),c[t].externalConnections=!0)}})):l.l.debug("Not a cluster ",t,h)}),e.edges().forEach(function(t){let r=e.edge(t);l.l.warn("Edge "+t.v+" -> "+t.w+": "+JSON.stringify(t)),l.l.warn("Edge "+t.v+" -> "+t.w+": "+JSON.stringify(e.edge(t)));let n=t.v,i=t.w;if(l.l.warn("Fix XXX",c,"ids:",t.v,t.w,"Translating: ",c[t.v]," --- ",c[t.w]),c[t.v]&&c[t.w]&&c[t.v]===c[t.w]){l.l.warn("Fixing and trixing link to self - removing XXX",t.v,t.w,t.name),l.l.warn("Fixing and trixing - removing XXX",t.v,t.w,t.name),n=getAnchorId(t.v),i=getAnchorId(t.w),e.removeEdge(t.v,t.w,t.name);let d=t.w+"---"+t.v;e.setNode(d,{domId:d,id:d,labelStyle:"",labelText:r.label,padding:0,shape:"labelRect",style:""});let a=structuredClone(r),s=structuredClone(r);a.label="",a.arrowTypeEnd="none",s.label="",a.fromCluster=t.v,s.toCluster=t.v,e.setEdge(n,d,a,t.name+"-cyclic-special"),e.setEdge(d,i,s,t.name+"-cyclic-special")}else(c[t.v]||c[t.w])&&(l.l.warn("Fixing and trixing - removing XXX",t.v,t.w,t.name),n=getAnchorId(t.v),i=getAnchorId(t.w),e.removeEdge(t.v,t.w,t.name),n!==t.v&&(r.fromCluster=t.v),i!==t.w&&(r.toCluster=t.w),l.l.warn("Fix Replacing with XXX",n,i,t.name),e.setEdge(n,i,r,t.name))}),l.l.warn("Adjusted Graph",i.c(e)),extractor(e,0),l.l.trace(c)},extractor=(e,t)=>{if(l.l.warn("extractor - ",t,i.c(e),e.children("D")),t>10){l.l.error("Bailing out");return}let r=e.nodes(),n=!1;for(let t of r){let r=e.children(t);n=n||r.length>0}if(!n){l.l.debug("Done, no node has children",e.nodes());return}for(let n of(l.l.debug("Nodes = ",r,t),r))if(l.l.debug("Extracting node",n,c,c[n]&&!c[n].externalConnections,!e.parent(n),e.node(n),e.children("D")," Depth ",t),c[n]){if(!c[n].externalConnections&&e.children(n)&&e.children(n).length>0){l.l.warn("Cluster without external connections, without a parent and with children",n,t);let r=e.graph(),d="TB"===r.rankdir?"LR":"TB";c[n]&&c[n].clusterData&&c[n].clusterData.dir&&(d=c[n].clusterData.dir,l.l.warn("Fixing dir",c[n].clusterData.dir,d));let s=new a.k({multigraph:!0,compound:!0}).setGraph({rankdir:d,nodesep:50,ranksep:50,marginx:8,marginy:8}).setDefaultEdgeLabel(function(){return{}});l.l.warn("Old graph before copy",i.c(e)),copy(n,e,s,n),e.setNode(n,{clusterNode:!0,id:n,clusterData:c[n].clusterData,labelText:c[n].labelText,graph:s}),l.l.warn("New graph after copy node: (",n,")",i.c(s)),l.l.debug("Old graph after copy",i.c(e))}else l.l.warn("Cluster ** ",n," **not meeting the criteria !externalConnections:",!c[n].externalConnections," no parent: ",!e.parent(n)," children ",e.children(n)&&e.children(n).length>0,e.children("D"),t),l.l.debug(c)}else l.l.debug("Not a cluster",n,t);for(let n of(r=e.nodes(),l.l.warn("New list of nodes",r),r)){let r=e.node(n);l.l.warn(" Now next level",n,r),r.clusterNode&&extractor(r.graph,t+1)}},sorter=(e,t)=>{if(0===t.length)return[];let r=Object.assign(t);return t.forEach(t=>{let n=e.children(t),i=sorter(e,n);r=[...r,...i]}),r},sortNodesByHierarchy=e=>sorter(e,e.children()),f={rect:(e,t)=>{l.l.info("Creating subgraph rect for ",t.id,t);let r=e.insert("g").attr("class","cluster"+(t.class?" "+t.class:"")).attr("id",t.id),n=r.insert("rect",":first-child"),i=(0,l.m)((0,l.c)().flowchart.htmlLabels),a=r.insert("g").attr("class","cluster-label"),c="markdown"===t.labelType?(0,s.a)(a,t.labelText,{style:t.labelStyle,useHtmlLabels:i}):a.node().appendChild((0,d.c)(t.labelText,t.labelStyle,void 0,!0)),h=c.getBBox();if((0,l.m)((0,l.c)().flowchart.htmlLabels)){let e=c.children[0],t=(0,o.Ys)(c);h=e.getBoundingClientRect(),t.attr("width",h.width),t.attr("height",h.height)}let g=0*t.padding,f=g/2,u=t.width<=h.width+g?h.width+g:t.width;t.width<=h.width+g?t.diff=(h.width-t.width)/2-t.padding/2:t.diff=-t.padding/2,l.l.trace("Data ",t,JSON.stringify(t)),n.attr("style",t.style).attr("rx",t.rx).attr("ry",t.ry).attr("x",t.x-u/2).attr("y",t.y-t.height/2-f).attr("width",u).attr("height",t.height+g),i?a.attr("transform","translate("+(t.x-h.width/2)+", "+(t.y-t.height/2)+")"):a.attr("transform","translate("+t.x+", "+(t.y-t.height/2)+")");let w=n.node().getBBox();return t.width=w.width,t.height=w.height,t.intersect=function(e){return(0,d.i)(t,e)},r},roundedWithTitle:(e,t)=>{let r=e.insert("g").attr("class",t.classes).attr("id",t.id),n=r.insert("rect",":first-child"),i=r.insert("g").attr("class","cluster-label"),a=r.append("rect"),s=i.node().appendChild((0,d.c)(t.labelText,t.labelStyle,void 0,!0)),c=s.getBBox();if((0,l.m)((0,l.c)().flowchart.htmlLabels)){let e=s.children[0],t=(0,o.Ys)(s);c=e.getBoundingClientRect(),t.attr("width",c.width),t.attr("height",c.height)}c=s.getBBox();let h=0*t.padding,g=h/2,f=t.width<=c.width+t.padding?c.width+t.padding:t.width;t.width<=c.width+t.padding?t.diff=(c.width+0*t.padding-t.width)/2:t.diff=-t.padding/2,n.attr("class","outer").attr("x",t.x-f/2-g).attr("y",t.y-t.height/2-g).attr("width",f+h).attr("height",t.height+h),a.attr("class","inner").attr("x",t.x-f/2-g).attr("y",t.y-t.height/2-g+c.height-1).attr("width",f+h).attr("height",t.height+h-c.height-3),i.attr("transform","translate("+(t.x-c.width/2)+", "+(t.y-t.height/2-t.padding/3+((0,l.m)((0,l.c)().flowchart.htmlLabels)?5:3))+")");let u=n.node().getBBox();return t.height=u.height,t.intersect=function(e){return(0,d.i)(t,e)},r},noteGroup:(e,t)=>{let r=e.insert("g").attr("class","note-cluster").attr("id",t.id),n=r.insert("rect",":first-child"),i=0*t.padding,l=i/2;n.attr("rx",t.rx).attr("ry",t.ry).attr("x",t.x-t.width/2-l).attr("y",t.y-t.height/2-l).attr("width",t.width+i).attr("height",t.height+i).attr("fill","none");let a=n.node().getBBox();return t.width=a.width,t.height=a.height,t.intersect=function(e){return(0,d.i)(t,e)},r},divider:(e,t)=>{let r=e.insert("g").attr("class",t.classes).attr("id",t.id),n=r.insert("rect",":first-child"),i=0*t.padding,l=i/2;n.attr("class","divider").attr("x",t.x-t.width/2-l).attr("y",t.y-t.height/2).attr("width",t.width+i).attr("height",t.height+i);let a=n.node().getBBox();return t.width=a.width,t.height=a.height,t.diff=-t.padding/2,t.intersect=function(e){return(0,d.i)(t,e)},r}},u={},insertCluster=(e,t)=>{l.l.trace("Inserting cluster");let r=t.shape||"rect";u[t.id]=f[r](e,t)},clear=()=>{u={}},recursiveRender=async(e,t,r,a,s)=>{l.l.info("Graph in recursive render: XXX",i.c(t),s);let o=t.graph().rankdir;l.l.trace("Dir in recursive render - dir:",o);let h=e.insert("g").attr("class","root");t.nodes()?l.l.info("Recursive render XXX",t.nodes()):l.l.info("No nodes found for",t),t.edges().length>0&&l.l.trace("Recursive edges",t.edge(t.edges()[0]));let g=h.insert("g").attr("class","clusters"),f=h.insert("g").attr("class","edgePaths"),u=h.insert("g").attr("class","edgeLabels"),w=h.insert("g").attr("class","nodes");await Promise.all(t.nodes().map(async function(e){let n=t.node(e);if(void 0!==s){let r=JSON.parse(JSON.stringify(s.clusterData));l.l.info("Setting data for cluster XXX (",e,") ",r,s),t.setNode(s.id,r),t.parent(e)||(l.l.trace("Setting parent",e,s.id),t.setParent(e,s.id,r))}if(l.l.info("(Insert) Node XXX"+e+": "+JSON.stringify(t.node(e))),n&&n.clusterNode){l.l.info("Cluster identified",e,n.width,t.node(e));let i=await recursiveRender(w,n.graph,r,a,t.node(e)),s=i.elem;(0,d.u)(n,s),n.diff=i.diff||0,l.l.info("Node bounds (abc123)",e,n,n.width,n.x,n.y),(0,d.s)(s,n),l.l.warn("Recursive render complete ",s,n)}else t.children(e).length>0?(l.l.info("Cluster - the non recursive path XXX",e,n.id,n,t),l.l.info(findNonClusterChild(n.id,t)),c[n.id]={id:findNonClusterChild(n.id,t),node:n}):(l.l.info("Node - the non recursive path",e,n.id,n),await (0,d.e)(w,t.node(e),o))})),t.edges().forEach(function(e){let r=t.edge(e.v,e.w,e.name);l.l.info("Edge "+e.v+" -> "+e.w+": "+JSON.stringify(e)),l.l.info("Edge "+e.v+" -> "+e.w+": ",e," ",JSON.stringify(t.edge(e))),l.l.info("Fix",c,"ids:",e.v,e.w,"Translateing: ",c[e.v],c[e.w]),(0,d.f)(u,r)}),t.edges().forEach(function(e){l.l.info("Edge "+e.v+" -> "+e.w+": "+JSON.stringify(e))}),l.l.info("#############################################"),l.l.info("###                Layout                 ###"),l.l.info("#############################################"),l.l.info(t),(0,n.bK)(t),l.l.info("Graph after layout:",i.c(t));let p=0;return sortNodesByHierarchy(t).forEach(function(e){let r=t.node(e);l.l.info("Position "+e+": "+JSON.stringify(t.node(e))),l.l.info("Position "+e+": ("+r.x,","+r.y,") width: ",r.width," height: ",r.height),r&&r.clusterNode?(0,d.p)(r):t.children(e).length>0?(insertCluster(g,r),c[r.id].node=r):(0,d.p)(r)}),t.edges().forEach(function(e){let n=t.edge(e);l.l.info("Edge "+e.v+" -> "+e.w+": "+JSON.stringify(n),n);let i=(0,d.g)(f,e,n,c,r,t,a);(0,d.h)(n,i)}),t.nodes().forEach(function(e){let r=t.node(e);l.l.info(e,r.type,r.diff),"group"===r.type&&(p=r.diff)}),{elem:h,diff:p}},render=async(e,t,r,n,a)=>{(0,d.a)(e,r,n,a),(0,d.b)(),(0,d.d)(),clear(),clear$1(),l.l.warn("Graph at first:",JSON.stringify(i.c(t))),adjustClustersAndEdges(t),l.l.warn("Graph after:",JSON.stringify(i.c(t))),await recursiveRender(e,t,n,a)}}}]);