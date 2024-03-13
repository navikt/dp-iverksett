(self.webpackChunk_N_E=self.webpackChunk_N_E||[]).push([[327],{2410:function(e,t,n){(window.__NEXT_P=window.__NEXT_P||[]).push(["/utvikling/feature_toggles",function(){return n(5733)}])},5733:function(e,t,n){"use strict";n.r(t),n.d(t,{__toc:function(){return l}});var r=n(5893),i=n(2673),s=n(7758),a=n(567);n(9128);var o=n(9530);let l=[{depth:2,value:"Stoppe iverksetting",id:"stoppe-iverksetting"}];function _createMdxContent(e){let t=Object.assign({h1:"h1",p:"p",a:"a",h2:"h2",ul:"ul",li:"li"},(0,o.a)(),e.components);return(0,r.jsxs)(r.Fragment,{children:[(0,r.jsx)(t.h1,{children:"Feature toggles"}),"\n",(0,r.jsxs)(t.p,{children:["Vi bruker Unleash for \xe5 st\xf8tte feature toggles i dp-iverksett. Kontrollpanel kan n\xe5s via naisdevice og finnes ",(0,r.jsx)(t.a,{href:"https://dagpenger-unleash-web.nav.cloud.nais.io/",children:"her"})]}),"\n",(0,r.jsx)(t.h2,{id:"stoppe-iverksetting",children:"Stoppe iverksetting"}),"\n",(0,r.jsxs)(t.p,{children:["Vi har opprettet ",(0,r.jsx)(t.a,{href:"https://www.getunleash.io/blog/kill-switches-best-practice",children:"kill switch"}),"-toggles per fagsystem for \xe5\nenkelt kunne stanse iverksetting av nye utbetalinger om det skulle bli behov for det. N\xe5r togglen er skrudd p\xe5 er\niverksetting av utbetalinger for gitt fagsystem stanset i gitt milj\xf8 (dev/prod)."]}),"\n",(0,r.jsxs)(t.ul,{children:["\n",(0,r.jsx)(t.li,{children:(0,r.jsx)(t.a,{href:"https://dagpenger-unleash-web.nav.cloud.nais.io/projects/default/features/dp-iverksett.stopp-iverksetting-dagpenger",children:"dp-iverksett.stopp-iverksetting-dagpenger"})}),"\n",(0,r.jsx)(t.li,{children:(0,r.jsx)(t.a,{href:"https://dagpenger-unleash-web.nav.cloud.nais.io/projects/default/features/dp-iverksett.stopp-iverksetting-tilleggsstonader",children:"dp-iverksett.stopp-iverksetting-tilleggsstonader"})}),"\n",(0,r.jsx)(t.li,{children:(0,r.jsx)(t.a,{href:"https://dagpenger-unleash-web.nav.cloud.nais.io/projects/default/features/dp-iverksett.stopp-iverksetting-tiltakspenger",children:"dp-iverksett.stopp-iverksetting-tiltakspenger"})}),"\n"]})]})}function MDXContent(){let e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{},{wrapper:t}=Object.assign({},(0,o.a)(),e.components);return t?(0,r.jsx)(t,{...e,children:(0,r.jsx)(_createMdxContent,{...e})}):_createMdxContent(e)}let g={MDXContent,pageOpts:{filePath:"pages/utvikling/feature_toggles.mdx",route:"/utvikling/feature_toggles",frontMatter:{title:"Feature Toggles"},pageMap:[{kind:"Meta",data:{index:"Om tjenesten",for_konsumenter:"For konsumenter",utvikling:"Utvikling"}},{kind:"Folder",name:"for_konsumenter",route:"/for_konsumenter",children:[{kind:"MdxPage",name:"avstemming",route:"/for_konsumenter/avstemming",frontMatter:{title:"Avstemming"}},{kind:"MdxPage",name:"iverksetting",route:"/for_konsumenter/iverksetting",frontMatter:{title:"Iverksetting"}},{kind:"MdxPage",name:"status",route:"/for_konsumenter/status",frontMatter:{title:"Status p\xe5 utbetaling"}},{kind:"Meta",data:{avstemming:"Avstemming",iverksetting:"Iverksetting",status:"Status p\xe5 utbetaling"}}]},{kind:"MdxPage",name:"for_konsumenter",route:"/for_konsumenter",frontMatter:{title:"For konsumenter"}},{kind:"MdxPage",name:"index",route:"/",frontMatter:{title:"Om tjenesten"}},{kind:"Folder",name:"utvikling",route:"/utvikling",children:[{kind:"MdxPage",name:"feature_toggles",route:"/utvikling/feature_toggles",frontMatter:{title:"Feature Toggles"}},{kind:"Meta",data:{feature_toggles:"Feature Toggles"}}]}],flexsearch:{codeblocks:!0},title:"Feature Toggles",headings:l},pageNextRoute:"/utvikling/feature_toggles",nextraLayout:s.ZP,themeConfig:a.Z};t.default=(0,i.j)(g)},567:function(e,t,n){"use strict";n.d(t,{Z:function(){return _}});var r=n(5893);n(7294);var i=n(1664),s=n.n(i),a=n(3419),o=n(5675),l=n.n(o),g=n(714),d=n(428),u=n(967),c=n.n(u),p=n(8639),k=n.n(p),h=n(3511),f=n(4442),v=n.n(f),x=n(7758),m=n(1163);let getTitle=e=>"".concat(e.title," - DP-Iverksett"),j={logo:(0,r.jsx)(()=>(0,r.jsxs)(g.U,{gap:"6",align:"center",children:[(0,r.jsx)(l(),{src:"/dp-iverksett/_next/static/media/nav-logo-red.d4f54b6e.svg",alt:"",width:"64",height:"20"}),(0,r.jsx)("span",{className:c().headerDivider}),(0,r.jsx)(d.Z,{weight:"semibold",size:"large",children:"DP-Iverksett"})]}),{}),project:{link:"https://github.com/navikt/dp-iverksett"},head:()=>{let{asPath:e,defaultLocale:t,locale:n}=(0,m.useRouter)(),{frontMatter:i}=(0,x.ZR)(),s="https://navikt.github.io/dp-iverksett"+(t===n?e:"/".concat(n).concat(e));return(0,r.jsxs)(r.Fragment,{children:[(0,r.jsx)("title",{children:getTitle(i)}),(0,r.jsx)("meta",{property:"og:url",content:s}),(0,r.jsx)("meta",{property:"og:title",content:"".concat(i.title," - DP-Iverksett")}),(0,r.jsx)("meta",{property:"og:description",content:i.description})]})},docsRepositoryBase:"https://github.com/navikt/dp-iverksett",footer:{text:"\xa9 2023 NAV IT"},feedback:{content:(0,r.jsx)(e=>{let{children:t}=e;return(0,r.jsxs)(a.Z,{className:v().label,children:[t," ",(0,r.jsx)(h.Z,{})]})},{children:"Gi oss tilbakemelding"})},editLink:{text:(0,r.jsx)(a.Z,{children:"Rediger denne siden"}),component:e=>{let{className:t,children:n,filePath:i}=e;return(0,r.jsx)(s(),{className:t,href:"https://github.com/navikt/dp-iverksett/blob/docs/".concat(i),children:n})}},toc:{title:(0,r.jsx)("span",{style:{color:"var(--a-text-default"},children:"P\xe5 denne siden"})},search:{placeholder:"S\xf8k i dokumentasjonen",emptyResult:(0,r.jsx)(()=>(0,r.jsx)(g.U,{className:k().container,justify:"center",align:"center",children:(0,r.jsx)(a.Z,{children:"Ingen treff"})}),{})}};var _=j},4442:function(e){e.exports={label:"ExternalLabel_label__BifXB"}},8639:function(e){e.exports={container:"IngenTreff_container__FJnjf"}},967:function(e){e.exports={headerDivider:"Logo_headerDivider__2JJVQ"}}},function(e){e.O(0,[73,774,888,179],function(){return e(e.s=2410)}),_N_E=e.O()}]);