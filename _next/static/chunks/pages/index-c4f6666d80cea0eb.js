(self.webpackChunk_N_E=self.webpackChunk_N_E||[]).push([[405],{1464:function(e,t,n){(window.__NEXT_P=window.__NEXT_P||[]).push(["/",function(){return n(9213)}])},9213:function(e,t,n){"use strict";n.r(t),n.d(t,{__toc:function(){return u}});var i=n(5893),r=n(2673),s=n(7758),o=n(567);n(9128);var a=n(9530),d=n(8757);let u=[{depth:2,value:"Seksjon 1",id:"seksjon-1"},{depth:2,value:"Seksjon 2",id:"seksjon-2"},{depth:2,value:"Seksjon 3",id:"seksjon-3"}];function _createMdxContent(e){let t=Object.assign({h1:"h1",p:"p",h2:"h2"},(0,a.a)(),e.components);return(0,i.jsxs)(i.Fragment,{children:[(0,i.jsx)(t.h1,{children:"DP-Iverksett"}),"\n",(0,i.jsx)(t.p,{children:"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."}),"\n",(0,i.jsx)(t.p,{children:"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."}),"\n",(0,i.jsx)(t.h2,{id:"seksjon-1",children:"Seksjon 1"}),"\n",(0,i.jsx)(t.h2,{id:"seksjon-2",children:"Seksjon 2"}),"\n",(0,i.jsx)(t.h2,{id:"seksjon-3",children:"Seksjon 3"}),"\n",(0,i.jsx)(d.G,{chart:'graph LR\n    subgraph dp-iverksett\n      iverksett["dp-iverksett"]\n      iverksettDB[(DB)]\n\n      iverksett --- iverksettDB\n    end\n\n    subgraph dp-oppdrag\n      oppdrag["dp-oppdrag"]\n      oppdragDB[(DB)]\n\n      oppdrag --- oppdragDB\n    end\n\n    vedtak["Vedtaksl\xf8sning"]\n    osur["OS/UR"]\n\n    vedtak -->|REST| dp-iverksett\n    dp-iverksett -->|REST| dp-oppdrag\n    dp-oppdrag ====>|Send-k\xf8| osur\n    dp-oppdrag ====>|Kvittering-k\xf8| osur\n    dp-oppdrag ====>|Avstemming-k\xf8| osur'})]})}function MDXContent(){let e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{},{wrapper:t}=Object.assign({},(0,a.a)(),e.components);return t?(0,i.jsx)(t,{...e,children:(0,i.jsx)(_createMdxContent,{...e})}):_createMdxContent(e)}let l={MDXContent,pageOpts:{filePath:"pages/index.mdx",route:"/",frontMatter:{title:"Om tjenesten"},pageMap:[{kind:"Meta",data:{index:"Om tjenesten",for_konsumenter:"For konsumenter",utvikling:"Utvikling"}},{kind:"Folder",name:"for_konsumenter",route:"/for_konsumenter",children:[{kind:"MdxPage",name:"avstemming",route:"/for_konsumenter/avstemming",frontMatter:{title:"Avstemming"}},{kind:"MdxPage",name:"iverksetting",route:"/for_konsumenter/iverksetting",frontMatter:{title:"Iverksetting"}},{kind:"MdxPage",name:"status",route:"/for_konsumenter/status",frontMatter:{title:"Status p\xe5 utbetaling"}},{kind:"Meta",data:{avstemming:"Avstemming",iverksetting:"Iverksetting",status:"Status p\xe5 utbetaling"}}]},{kind:"MdxPage",name:"for_konsumenter",route:"/for_konsumenter",frontMatter:{title:"For konsumenter"}},{kind:"MdxPage",name:"index",route:"/",frontMatter:{title:"Om tjenesten"}},{kind:"MdxPage",name:"utvikling",route:"/utvikling"}],flexsearch:{codeblocks:!0},title:"Om tjenesten",headings:u},pageNextRoute:"/",nextraLayout:s.ZP,themeConfig:o.Z};t.default=(0,r.j)(l)},567:function(e,t,n){"use strict";n.d(t,{Z:function(){return _}});var i=n(5893);n(7294);var r=n(1664),s=n.n(r),o=n(3419),a=n(5675),d=n.n(a),u=n(714),l=n(428),c=n(967),p=n.n(c),m=n(8639),k=n.n(m),g=n(3511),x=n(4442),h=n.n(x),v=n(7758),f=n(1163);let j={logo:(0,i.jsx)(()=>(0,i.jsxs)(u.U,{gap:"6",align:"center",children:[(0,i.jsx)(d(),{src:"/dp-iverksett/_next/static/media/nav-logo-red.d4f54b6e.svg",alt:"",width:"64",height:"20"}),(0,i.jsx)("span",{className:p().headerDivider}),(0,i.jsx)(l.Z,{weight:"semibold",size:"large",children:"DP-Iverksett"})]}),{}),project:{link:"https://github.com/navikt/dp-iverksett"},head:()=>{let{asPath:e,defaultLocale:t,locale:n}=(0,f.useRouter)(),{frontMatter:r}=(0,v.ZR)(),s="https://navikt.github.io/dp-iverksett"+(t===n?e:"/".concat(n).concat(e));return console.log(r),(0,i.jsxs)(i.Fragment,{children:[(0,i.jsxs)("title",{children:[r.title," - DP-Iverksett"]}),(0,i.jsx)("meta",{property:"og:url",content:s}),(0,i.jsx)("meta",{property:"og:title",content:"".concat(r.title," - DP-Iverksett")}),(0,i.jsx)("meta",{property:"og:description",content:r.description})]})},docsRepositoryBase:"https://github.com/navikt/dp-iverksett",footer:{text:"\xa9 2023 NAV IT"},feedback:{content:(0,i.jsx)(e=>{let{children:t}=e;return(0,i.jsxs)(o.Z,{className:h().label,children:[t," ",(0,i.jsx)(g.Z,{})]})},{children:"Gi oss tilbakemelding"})},editLink:{text:(0,i.jsx)(o.Z,{children:"Rediger denne siden"}),component:e=>{let{className:t,children:n,filePath:r}=e;return(0,i.jsx)(s(),{className:t,href:"https://github.com/navikt/dp-iverksett/blob/docs/".concat(r),children:n})}},toc:{title:(0,i.jsx)("span",{style:{color:"var(--a-text-default"},children:"P\xe5 denne siden"})},search:{placeholder:"S\xf8k i dokumentasjonen",emptyResult:(0,i.jsx)(()=>(0,i.jsx)(u.U,{className:k().container,justify:"center",align:"center",children:(0,i.jsx)(o.Z,{children:"Ingen treff"})}),{})}};var _=j},4442:function(e){e.exports={label:"ExternalLabel_label__BifXB"}},8639:function(e){e.exports={container:"IngenTreff_container__FJnjf"}},967:function(e){e.exports={headerDivider:"Logo_headerDivider__2JJVQ"}}},function(e){e.O(0,[241,73,202,774,888,179],function(){return e(e.s=1464)}),_N_E=e.O()}]);