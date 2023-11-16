(self.webpackChunk_N_E=self.webpackChunk_N_E||[]).push([[7],{3601:function(e,t,n){(window.__NEXT_P=window.__NEXT_P||[]).push(["/for_konsumenter/status",function(){return n(7232)}])},7232:function(e,t,n){"use strict";n.r(t),n.d(t,{__toc:function(){return d}});var r=n(5893),s=n(2673),i=n(7758),a=n(567);n(9128);var o=n(9530);let d=[{depth:2,value:"GET /{behandlingId}/status",id:"get-behandlingidstatus"}];function _createMdxContent(e){let t=Object.assign({h2:"h2",p:"p",code:"code",table:"table",thead:"thead",tr:"tr",th:"th",tbody:"tbody",td:"td"},(0,o.a)(),e.components);return(0,r.jsxs)(r.Fragment,{children:[(0,r.jsx)(t.h2,{id:"get-behandlingidstatus",children:"GET /{behandlingId}/status"}),"\n",(0,r.jsx)(t.p,{children:"Henter status p\xe5 utbetaling. Dette endepunktet kan brukes for \xe5 sjekke tilstanden til en utbetaling som er tatt i mot og\nvalidert av dp-iverksett. NAVs \xf8konomisystem har en asynkron integrasjon og er i tillegg et stormaskinsystem som har definerte\noppetider. \xd8konomisystemet er stengt mellom i helger, helligdager og mellom kl 21 og kl 6 p\xe5 virkedager. Derfor kan det\nta lang tid f\xf8r en utbetaling blir kvittert ut fra \xf8konomisystemet avhengig av n\xe5r den er sendt inn."}),"\n",(0,r.jsxs)(t.p,{children:["Dersom dp-iverksett finner behandlingen, vil endepunktet svare med ",(0,r.jsx)(t.code,{children:"200 OK"})," og en av f\xf8lgende statuser i body:"]}),"\n",(0,r.jsxs)(t.table,{children:[(0,r.jsx)(t.thead,{children:(0,r.jsxs)(t.tr,{children:[(0,r.jsx)(t.th,{children:"Status"}),(0,r.jsx)(t.th,{children:"Beskrivelse"})]})}),(0,r.jsxs)(t.tbody,{children:[(0,r.jsxs)(t.tr,{children:[(0,r.jsx)(t.td,{children:"IKKE_PAABEGYNT"}),(0,r.jsx)(t.td,{children:"Ikke sendt til \xf8konomisystemet"})]}),(0,r.jsxs)(t.tr,{children:[(0,r.jsx)(t.td,{children:"SENDT_TIL_OPPDRAG"}),(0,r.jsx)(t.td,{children:"Sendt til \xf8konomisystemet, venter p\xe5 svar"})]}),(0,r.jsxs)(t.tr,{children:[(0,r.jsx)(t.td,{children:"FEILET_MOT_OPPDRAG"}),(0,r.jsx)(t.td,{children:"Feilkvittering fra \xf8konomisystemet. Kan v\xe6re enten teknisk eller funksjonell feil."})]}),(0,r.jsxs)(t.tr,{children:[(0,r.jsx)(t.td,{children:"OK"}),(0,r.jsx)(t.td,{children:"Kvittert OK fra \xf8konomisystemet og ferdigstilt."})]})]})]}),"\n",(0,r.jsx)(t.p,{children:"NB! Vi har mulighet til \xe5 tilby et Kafka-topic der konsumentene kan lytte p\xe5 status-oppdateringer p\xe5 utbetalinger. Dette er p.t.\nikke satt opp, men vi bruker gjerne tid p\xe5 det dersom behovet er der. Ta kontakt med oss dersom dette h\xf8res interessant ut\nfor dere."})]})}function MDXContent(){let e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{},{wrapper:t}=Object.assign({},(0,o.a)(),e.components);return t?(0,r.jsx)(t,{...e,children:(0,r.jsx)(_createMdxContent,{...e})}):_createMdxContent(e)}let l={MDXContent,pageOpts:{filePath:"pages/for_konsumenter/status.mdx",route:"/for_konsumenter/status",frontMatter:{title:"Status p\xe5 utbetaling"},pageMap:[{kind:"Meta",data:{index:"Om tjenesten",for_konsumenter:"For konsumenter",utvikling:"Utvikling"}},{kind:"Folder",name:"for_konsumenter",route:"/for_konsumenter",children:[{kind:"MdxPage",name:"avstemming",route:"/for_konsumenter/avstemming",frontMatter:{title:"Avstemming"}},{kind:"MdxPage",name:"iverksetting",route:"/for_konsumenter/iverksetting",frontMatter:{title:"Iverksetting"}},{kind:"MdxPage",name:"status",route:"/for_konsumenter/status",frontMatter:{title:"Status p\xe5 utbetaling"}},{kind:"Meta",data:{avstemming:"Avstemming",iverksetting:"Iverksetting",status:"Status p\xe5 utbetaling"}}]},{kind:"MdxPage",name:"for_konsumenter",route:"/for_konsumenter",frontMatter:{title:"For konsumenter"}},{kind:"MdxPage",name:"index",route:"/",frontMatter:{title:"Om tjenesten"}},{kind:"MdxPage",name:"utvikling",route:"/utvikling"}],flexsearch:{codeblocks:!0},title:"Status p\xe5 utbetaling",headings:d},pageNextRoute:"/for_konsumenter/status",nextraLayout:i.ZP,themeConfig:a.Z};t.default=(0,s.j)(l)},567:function(e,t,n){"use strict";n.d(t,{Z:function(){return b}});var r=n(5893);n(7294);var s=n(1664),i=n.n(s),a=n(3419),o=n(5675),d=n.n(o),l=n(714),c=n(428),u=n(967),x=n.n(u),k=n(8639),g=n.n(k),h=n(3511),m=n(4442),f=n.n(m),p=n(7758),j=n(1163);let v={logo:(0,r.jsx)(()=>(0,r.jsxs)(l.U,{gap:"6",align:"center",children:[(0,r.jsx)(d(),{src:"/dp-iverksett/_next/static/media/nav-logo-red.d4f54b6e.svg",alt:"",width:"64",height:"20"}),(0,r.jsx)("span",{className:x().headerDivider}),(0,r.jsx)(c.Z,{weight:"semibold",size:"large",children:"DP-Iverksett"})]}),{}),project:{link:"https://github.com/navikt/dp-iverksett"},head:()=>{let{asPath:e,defaultLocale:t,locale:n}=(0,j.useRouter)(),{frontMatter:s}=(0,p.ZR)(),i="https://navikt.github.io/dp-iverksett"+(t===n?e:"/".concat(n).concat(e));return console.log(s),(0,r.jsxs)(r.Fragment,{children:[(0,r.jsxs)("title",{children:[s.title," - DP-Iverksett"]}),(0,r.jsx)("meta",{property:"og:url",content:i}),(0,r.jsx)("meta",{property:"og:title",content:"".concat(s.title," - DP-Iverksett")}),(0,r.jsx)("meta",{property:"og:description",content:s.description})]})},docsRepositoryBase:"https://github.com/navikt/dp-iverksett",footer:{text:"\xa9 2023 NAV IT"},feedback:{content:(0,r.jsx)(e=>{let{children:t}=e;return(0,r.jsxs)(a.Z,{className:f().label,children:[t," ",(0,r.jsx)(h.Z,{})]})},{children:"Gi oss tilbakemelding"})},editLink:{text:(0,r.jsx)(a.Z,{children:"Rediger denne siden"}),component:e=>{let{className:t,children:n,filePath:s}=e;return(0,r.jsx)(i(),{className:t,href:"https://github.com/navikt/dp-iverksett/blob/docs/".concat(s),children:n})}},toc:{title:(0,r.jsx)("span",{style:{color:"var(--a-text-default"},children:"P\xe5 denne siden"})},search:{placeholder:"S\xf8k i dokumentasjonen",emptyResult:(0,r.jsx)(()=>(0,r.jsx)(l.U,{className:g().container,justify:"center",align:"center",children:(0,r.jsx)(a.Z,{children:"Ingen treff"})}),{})}};var b=v},4442:function(e){e.exports={label:"ExternalLabel_label__BifXB"}},8639:function(e){e.exports={container:"IngenTreff_container__FJnjf"}},967:function(e){e.exports={headerDivider:"Logo_headerDivider__2JJVQ"}}},function(e){e.O(0,[73,774,888,179],function(){return e(e.s=3601)}),_N_E=e.O()}]);