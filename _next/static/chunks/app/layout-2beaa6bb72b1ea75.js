(self.webpackChunk_N_E=self.webpackChunk_N_E||[]).push([[185],{6956:function(e,n,t){Promise.resolve().then(t.t.bind(t,1309,23)),Promise.resolve().then(t.t.bind(t,2853,23)),Promise.resolve().then(t.bind(t,4487)),Promise.resolve().then(t.t.bind(t,5761,23)),Promise.resolve().then(t.t.bind(t,4390,23)),Promise.resolve().then(t.t.bind(t,413,23)),Promise.resolve().then(t.t.bind(t,8326,23)),Promise.resolve().then(t.t.bind(t,9259,23)),Promise.resolve().then(t.bind(t,6172))},4487:function(e,n,t){"use strict";t.r(n),t.d(n,{ExpandableNavLink:function(){return ExpandableNavLink}});var i=t(7437),r=t(2265),a=t(1396),s=t.n(a),l=t(4033),o=t(6198),c=t(8898),d=t(1165),u=t(7532),h=t(4390),_=t.n(h);let m=r.forwardRef((e,n)=>{let{children:t,...r}=e;return(0,i.jsx)(u.Z,{...r,className:_().link,children:t})});function formaterNavn(e){return e[0].toUpperCase()+e.slice(1).replace("_"," ")}function NavLink(e){let{name:n,subDirectories:t,href:r}=e;return Object.keys(t).length>0?(0,i.jsx)(ExpandableNavLink,{name:n,subDirectories:t,href:r}):(0,i.jsx)("li",{children:(0,i.jsx)(s(),{href:r,legacyBehavior:!0,children:(0,i.jsx)(m,{children:(0,i.jsx)(c.Z,{children:formaterNavn(n)})})})},n)}m.displayName="MenuLink";var v=t(7591),x=t(5),f=t(2995),b=t.n(f);function ExpandableNavLink(e){let{name:n,subDirectories:t,href:a}=e,[u,h]=(0,r.useState)(!1),_=(0,l.usePathname)();return(0,r.useLayoutEffect)(()=>{h(_.includes(a))},[a,_]),(0,i.jsxs)("li",{className:b().listItem,children:[(0,i.jsxs)(o.U,{wrap:!1,className:b().linkAndButton,children:[(0,i.jsx)(s(),{href:a,legacyBehavior:!0,children:(0,i.jsx)(m,{children:(0,i.jsx)(c.Z,{children:formaterNavn(n)})})}),(0,i.jsx)(d.Z,{onClick:e=>{e.preventDefault(),h(e=>!e)},variant:"tertiary-neutral",size:"xsmall",children:u?(0,i.jsx)(v.Z,{}):(0,i.jsx)(x.Z,{})})]}),u&&(0,i.jsx)("ul",{className:b().links,children:Object.entries(t).map(e=>{let[n,t]=e;return(0,i.jsx)(NavLink,{name:n,subDirectories:t,href:"".concat(a,"/").concat(n)},n)})})]},n)}},2853:function(){},1309:function(e){e.exports={body:"layout_body__7rhn1",header:"layout_header__mmmNG",headerDivider:"layout_headerDivider__IgZzZ",headerLink:"layout_headerLink__yLFYr",container:"layout_container__Yo0Pg",sideBar:"layout_sideBar__wxLCi",mainContent:"layout_mainContent__0mFuc",mdxContent:"layout_mdxContent__pCX5V"}},2995:function(e){e.exports={listItem:"ExpandableNavLink_listItem__eVH0C",links:"ExpandableNavLink_links__l1VoP"}},4390:function(e){e.exports={link:"MenuLink_link__HT0Sc"}},5761:function(e){e.exports={nav:"Nav_nav__o8sSc"}},6172:function(e,n,t){"use strict";t.r(n),n.default={src:"/dp-iverksett/_next/static/media/nav-logo-red.d4f54b6e.svg",height:20,width:64,blurWidth:0,blurHeight:0}}},function(e){e.O(0,[519,483,971,472,744],function(){return e(e.s=6956)}),_N_E=e.O()}]);