"use strict";(self.webpackChunk_N_E=self.webpackChunk_N_E||[]).push([[845],{3845:function(t,i,e){let s;e.d(i,{diagram:function(){return y}});var a=e(4981),n=e(2319),h=e(9868);e(4548),e(1417),e(3571);var r=function(){var o=function(t,i,e,s){for(e=e||{},s=t.length;s--;e[t[s]]=i);return e},t=[1,10,12,14,16,18,19,21,23],i=[2,6],e=[1,3],s=[1,5],a=[1,6],n=[1,7],h=[1,5,10,12,14,16,18,19,21,23,34,35,36],r=[1,25],l=[1,26],c=[1,28],g=[1,29],u=[1,30],x=[1,31],d=[1,32],p=[1,33],f=[1,34],y=[1,35],m=[1,36],b=[1,37],A=[1,43],C=[1,42],S=[1,47],w=[1,50],k=[1,10,12,14,16,18,19,21,23,34,35,36],_=[1,10,12,14,16,18,19,21,23,24,26,27,28,34,35,36],T=[1,10,12,14,16,18,19,21,23,24,26,27,28,34,35,36,41,42,43,44,45,46,47,48,49,50],R=[1,64],D={trace:function(){},yy:{},symbols_:{error:2,start:3,eol:4,XYCHART:5,chartConfig:6,document:7,CHART_ORIENTATION:8,statement:9,title:10,text:11,X_AXIS:12,parseXAxis:13,Y_AXIS:14,parseYAxis:15,LINE:16,plotData:17,BAR:18,acc_title:19,acc_title_value:20,acc_descr:21,acc_descr_value:22,acc_descr_multiline_value:23,SQUARE_BRACES_START:24,commaSeparatedNumbers:25,SQUARE_BRACES_END:26,NUMBER_WITH_DECIMAL:27,COMMA:28,xAxisData:29,bandData:30,ARROW_DELIMITER:31,commaSeparatedTexts:32,yAxisData:33,NEWLINE:34,SEMI:35,EOF:36,alphaNum:37,STR:38,MD_STR:39,alphaNumToken:40,AMP:41,NUM:42,ALPHA:43,PLUS:44,EQUALS:45,MULT:46,DOT:47,BRKT:48,MINUS:49,UNDERSCORE:50,$accept:0,$end:1},terminals_:{2:"error",5:"XYCHART",8:"CHART_ORIENTATION",10:"title",12:"X_AXIS",14:"Y_AXIS",16:"LINE",18:"BAR",19:"acc_title",20:"acc_title_value",21:"acc_descr",22:"acc_descr_value",23:"acc_descr_multiline_value",24:"SQUARE_BRACES_START",26:"SQUARE_BRACES_END",27:"NUMBER_WITH_DECIMAL",28:"COMMA",31:"ARROW_DELIMITER",34:"NEWLINE",35:"SEMI",36:"EOF",38:"STR",39:"MD_STR",41:"AMP",42:"NUM",43:"ALPHA",44:"PLUS",45:"EQUALS",46:"MULT",47:"DOT",48:"BRKT",49:"MINUS",50:"UNDERSCORE"},productions_:[0,[3,2],[3,3],[3,2],[3,1],[6,1],[7,0],[7,2],[9,2],[9,2],[9,2],[9,2],[9,2],[9,3],[9,2],[9,3],[9,2],[9,2],[9,1],[17,3],[25,3],[25,1],[13,1],[13,2],[13,1],[29,1],[29,3],[30,3],[32,3],[32,1],[15,1],[15,2],[15,1],[33,3],[4,1],[4,1],[4,1],[11,1],[11,1],[11,1],[37,1],[37,2],[40,1],[40,1],[40,1],[40,1],[40,1],[40,1],[40,1],[40,1],[40,1],[40,1]],performAction:function(t,i,e,s,a,n,h){var r=n.length-1;switch(a){case 5:s.setOrientation(n[r]);break;case 9:s.setDiagramTitle(n[r].text.trim());break;case 12:s.setLineData({text:"",type:"text"},n[r]);break;case 13:s.setLineData(n[r-1],n[r]);break;case 14:s.setBarData({text:"",type:"text"},n[r]);break;case 15:s.setBarData(n[r-1],n[r]);break;case 16:this.$=n[r].trim(),s.setAccTitle(this.$);break;case 17:case 18:this.$=n[r].trim(),s.setAccDescription(this.$);break;case 19:case 27:this.$=n[r-1];break;case 20:this.$=[Number(n[r-2]),...n[r]];break;case 21:this.$=[Number(n[r])];break;case 22:s.setXAxisTitle(n[r]);break;case 23:s.setXAxisTitle(n[r-1]);break;case 24:s.setXAxisTitle({type:"text",text:""});break;case 25:s.setXAxisBand(n[r]);break;case 26:s.setXAxisRangeData(Number(n[r-2]),Number(n[r]));break;case 28:this.$=[n[r-2],...n[r]];break;case 29:this.$=[n[r]];break;case 30:s.setYAxisTitle(n[r]);break;case 31:s.setYAxisTitle(n[r-1]);break;case 32:s.setYAxisTitle({type:"text",text:""});break;case 33:s.setYAxisRangeData(Number(n[r-2]),Number(n[r]));break;case 37:case 38:this.$={text:n[r],type:"text"};break;case 39:this.$={text:n[r],type:"markdown"};break;case 40:this.$=n[r];break;case 41:this.$=n[r-1]+""+n[r]}},table:[o(t,i,{3:1,4:2,7:4,5:e,34:s,35:a,36:n}),{1:[3]},o(t,i,{4:2,7:4,3:8,5:e,34:s,35:a,36:n}),o(t,i,{4:2,7:4,6:9,3:10,5:e,8:[1,11],34:s,35:a,36:n}),{1:[2,4],9:12,10:[1,13],12:[1,14],14:[1,15],16:[1,16],18:[1,17],19:[1,18],21:[1,19],23:[1,20]},o(h,[2,34]),o(h,[2,35]),o(h,[2,36]),{1:[2,1]},o(t,i,{4:2,7:4,3:21,5:e,34:s,35:a,36:n}),{1:[2,3]},o(h,[2,5]),o(t,[2,7],{4:22,34:s,35:a,36:n}),{11:23,37:24,38:r,39:l,40:27,41:c,42:g,43:u,44:x,45:d,46:p,47:f,48:y,49:m,50:b},{11:39,13:38,24:A,27:C,29:40,30:41,37:24,38:r,39:l,40:27,41:c,42:g,43:u,44:x,45:d,46:p,47:f,48:y,49:m,50:b},{11:45,15:44,27:S,33:46,37:24,38:r,39:l,40:27,41:c,42:g,43:u,44:x,45:d,46:p,47:f,48:y,49:m,50:b},{11:49,17:48,24:w,37:24,38:r,39:l,40:27,41:c,42:g,43:u,44:x,45:d,46:p,47:f,48:y,49:m,50:b},{11:52,17:51,24:w,37:24,38:r,39:l,40:27,41:c,42:g,43:u,44:x,45:d,46:p,47:f,48:y,49:m,50:b},{20:[1,53]},{22:[1,54]},o(k,[2,18]),{1:[2,2]},o(k,[2,8]),o(k,[2,9]),o(_,[2,37],{40:55,41:c,42:g,43:u,44:x,45:d,46:p,47:f,48:y,49:m,50:b}),o(_,[2,38]),o(_,[2,39]),o(T,[2,40]),o(T,[2,42]),o(T,[2,43]),o(T,[2,44]),o(T,[2,45]),o(T,[2,46]),o(T,[2,47]),o(T,[2,48]),o(T,[2,49]),o(T,[2,50]),o(T,[2,51]),o(k,[2,10]),o(k,[2,22],{30:41,29:56,24:A,27:C}),o(k,[2,24]),o(k,[2,25]),{31:[1,57]},{11:59,32:58,37:24,38:r,39:l,40:27,41:c,42:g,43:u,44:x,45:d,46:p,47:f,48:y,49:m,50:b},o(k,[2,11]),o(k,[2,30],{33:60,27:S}),o(k,[2,32]),{31:[1,61]},o(k,[2,12]),{17:62,24:w},{25:63,27:R},o(k,[2,14]),{17:65,24:w},o(k,[2,16]),o(k,[2,17]),o(T,[2,41]),o(k,[2,23]),{27:[1,66]},{26:[1,67]},{26:[2,29],28:[1,68]},o(k,[2,31]),{27:[1,69]},o(k,[2,13]),{26:[1,70]},{26:[2,21],28:[1,71]},o(k,[2,15]),o(k,[2,26]),o(k,[2,27]),{11:59,32:72,37:24,38:r,39:l,40:27,41:c,42:g,43:u,44:x,45:d,46:p,47:f,48:y,49:m,50:b},o(k,[2,33]),o(k,[2,19]),{25:73,27:R},{26:[2,28]},{26:[2,20]}],defaultActions:{8:[2,1],10:[2,3],21:[2,2],72:[2,28],73:[2,20]},parseError:function(t,i){if(i.recoverable)this.trace(t);else{var e=Error(t);throw e.hash=i,e}},parse:function(t){var i=this,e=[0],s=[],a=[null],n=[],h=this.table,r="",l=0,c=0,g=n.slice.call(arguments,1),u=Object.create(this.lexer),x={yy:{}};for(var d in this.yy)Object.prototype.hasOwnProperty.call(this.yy,d)&&(x.yy[d]=this.yy[d]);u.setInput(t,x.yy),x.yy.lexer=u,x.yy.parser=this,void 0===u.yylloc&&(u.yylloc={});var p=u.yylloc;n.push(p);var f=u.options&&u.options.ranges;function lex(){var t;return"number"!=typeof(t=s.pop()||u.lex()||1)&&(t instanceof Array&&(t=(s=t).pop()),t=i.symbols_[t]||t),t}"function"==typeof x.yy.parseError?this.parseError=x.yy.parseError:this.parseError=Object.getPrototypeOf(this).parseError;for(var y,m,b,A,C,S,w,k,_={};;){if(m=e[e.length-1],this.defaultActions[m]?b=this.defaultActions[m]:(null==y&&(y=lex()),b=h[m]&&h[m][y]),void 0===b||!b.length||!b[0]){var T="";for(C in k=[],h[m])this.terminals_[C]&&C>2&&k.push("'"+this.terminals_[C]+"'");T=u.showPosition?"Parse error on line "+(l+1)+":\n"+u.showPosition()+"\nExpecting "+k.join(", ")+", got '"+(this.terminals_[y]||y)+"'":"Parse error on line "+(l+1)+": Unexpected "+(1==y?"end of input":"'"+(this.terminals_[y]||y)+"'"),this.parseError(T,{text:u.match,token:this.terminals_[y]||y,line:u.yylineno,loc:p,expected:k})}if(b[0]instanceof Array&&b.length>1)throw Error("Parse Error: multiple actions possible at state: "+m+", token: "+y);switch(b[0]){case 1:e.push(y),a.push(u.yytext),n.push(u.yylloc),e.push(b[1]),y=null,c=u.yyleng,r=u.yytext,l=u.yylineno,p=u.yylloc;break;case 2:if(S=this.productions_[b[1]][1],_.$=a[a.length-S],_._$={first_line:n[n.length-(S||1)].first_line,last_line:n[n.length-1].last_line,first_column:n[n.length-(S||1)].first_column,last_column:n[n.length-1].last_column},f&&(_._$.range=[n[n.length-(S||1)].range[0],n[n.length-1].range[1]]),void 0!==(A=this.performAction.apply(_,[r,c,l,x.yy,b[1],a,n].concat(g))))return A;S&&(e=e.slice(0,-1*S*2),a=a.slice(0,-1*S),n=n.slice(0,-1*S)),e.push(this.productions_[b[1]][0]),a.push(_.$),n.push(_._$),w=h[e[e.length-2]][e[e.length-1]],e.push(w);break;case 3:return!0}}return!0}};function Parser(){this.yy={}}return D.lexer={EOF:1,parseError:function(t,i){if(this.yy.parser)this.yy.parser.parseError(t,i);else throw Error(t)},setInput:function(t,i){return this.yy=i||this.yy||{},this._input=t,this._more=this._backtrack=this.done=!1,this.yylineno=this.yyleng=0,this.yytext=this.matched=this.match="",this.conditionStack=["INITIAL"],this.yylloc={first_line:1,first_column:0,last_line:1,last_column:0},this.options.ranges&&(this.yylloc.range=[0,0]),this.offset=0,this},input:function(){var t=this._input[0];return this.yytext+=t,this.yyleng++,this.offset++,this.match+=t,this.matched+=t,t.match(/(?:\r\n?|\n).*/g)?(this.yylineno++,this.yylloc.last_line++):this.yylloc.last_column++,this.options.ranges&&this.yylloc.range[1]++,this._input=this._input.slice(1),t},unput:function(t){var i=t.length,e=t.split(/(?:\r\n?|\n)/g);this._input=t+this._input,this.yytext=this.yytext.substr(0,this.yytext.length-i),this.offset-=i;var s=this.match.split(/(?:\r\n?|\n)/g);this.match=this.match.substr(0,this.match.length-1),this.matched=this.matched.substr(0,this.matched.length-1),e.length-1&&(this.yylineno-=e.length-1);var a=this.yylloc.range;return this.yylloc={first_line:this.yylloc.first_line,last_line:this.yylineno+1,first_column:this.yylloc.first_column,last_column:e?(e.length===s.length?this.yylloc.first_column:0)+s[s.length-e.length].length-e[0].length:this.yylloc.first_column-i},this.options.ranges&&(this.yylloc.range=[a[0],a[0]+this.yyleng-i]),this.yyleng=this.yytext.length,this},more:function(){return this._more=!0,this},reject:function(){return this.options.backtrack_lexer?(this._backtrack=!0,this):this.parseError("Lexical error on line "+(this.yylineno+1)+". You can only invoke reject() in the lexer when the lexer is of the backtracking persuasion (options.backtrack_lexer = true).\n"+this.showPosition(),{text:"",token:null,line:this.yylineno})},less:function(t){this.unput(this.match.slice(t))},pastInput:function(){var t=this.matched.substr(0,this.matched.length-this.match.length);return(t.length>20?"...":"")+t.substr(-20).replace(/\n/g,"")},upcomingInput:function(){var t=this.match;return t.length<20&&(t+=this._input.substr(0,20-t.length)),(t.substr(0,20)+(t.length>20?"...":"")).replace(/\n/g,"")},showPosition:function(){var t=this.pastInput(),i=Array(t.length+1).join("-");return t+this.upcomingInput()+"\n"+i+"^"},test_match:function(t,i){var e,s,a;if(this.options.backtrack_lexer&&(a={yylineno:this.yylineno,yylloc:{first_line:this.yylloc.first_line,last_line:this.last_line,first_column:this.yylloc.first_column,last_column:this.yylloc.last_column},yytext:this.yytext,match:this.match,matches:this.matches,matched:this.matched,yyleng:this.yyleng,offset:this.offset,_more:this._more,_input:this._input,yy:this.yy,conditionStack:this.conditionStack.slice(0),done:this.done},this.options.ranges&&(a.yylloc.range=this.yylloc.range.slice(0))),(s=t[0].match(/(?:\r\n?|\n).*/g))&&(this.yylineno+=s.length),this.yylloc={first_line:this.yylloc.last_line,last_line:this.yylineno+1,first_column:this.yylloc.last_column,last_column:s?s[s.length-1].length-s[s.length-1].match(/\r?\n?/)[0].length:this.yylloc.last_column+t[0].length},this.yytext+=t[0],this.match+=t[0],this.matches=t,this.yyleng=this.yytext.length,this.options.ranges&&(this.yylloc.range=[this.offset,this.offset+=this.yyleng]),this._more=!1,this._backtrack=!1,this._input=this._input.slice(t[0].length),this.matched+=t[0],e=this.performAction.call(this,this.yy,this,i,this.conditionStack[this.conditionStack.length-1]),this.done&&this._input&&(this.done=!1),e)return e;if(this._backtrack)for(var n in a)this[n]=a[n];return!1},next:function(){if(this.done)return this.EOF;this._input||(this.done=!0),this._more||(this.yytext="",this.match="");for(var t,i,e,s,a=this._currentRules(),n=0;n<a.length;n++)if((e=this._input.match(this.rules[a[n]]))&&(!i||e[0].length>i[0].length)){if(i=e,s=n,this.options.backtrack_lexer){if(!1!==(t=this.test_match(e,a[n])))return t;if(!this._backtrack)return!1;i=!1;continue}if(!this.options.flex)break}return i?!1!==(t=this.test_match(i,a[s]))&&t:""===this._input?this.EOF:this.parseError("Lexical error on line "+(this.yylineno+1)+". Unrecognized text.\n"+this.showPosition(),{text:"",token:null,line:this.yylineno})},lex:function(){return this.next()||this.lex()},begin:function(t){this.conditionStack.push(t)},popState:function(){return this.conditionStack.length-1>0?this.conditionStack.pop():this.conditionStack[0]},_currentRules:function(){return this.conditionStack.length&&this.conditionStack[this.conditionStack.length-1]?this.conditions[this.conditionStack[this.conditionStack.length-1]].rules:this.conditions.INITIAL.rules},topState:function(t){return(t=this.conditionStack.length-1-Math.abs(t||0))>=0?this.conditionStack[t]:"INITIAL"},pushState:function(t){this.begin(t)},stateStackSize:function(){return this.conditionStack.length},options:{"case-insensitive":!0},performAction:function(t,i,e,s){switch(e){case 0:case 1:case 5:case 43:break;case 2:case 3:return this.popState(),34;case 4:return 34;case 6:return 10;case 7:return this.pushState("acc_title"),19;case 8:return this.popState(),"acc_title_value";case 9:return this.pushState("acc_descr"),21;case 10:return this.popState(),"acc_descr_value";case 11:this.pushState("acc_descr_multiline");break;case 12:case 25:case 27:this.popState();break;case 13:return"acc_descr_multiline_value";case 14:return 5;case 15:return 8;case 16:return this.pushState("axis_data"),"X_AXIS";case 17:return this.pushState("axis_data"),"Y_AXIS";case 18:return this.pushState("axis_band_data"),24;case 19:return 31;case 20:return this.pushState("data"),16;case 21:return this.pushState("data"),18;case 22:return this.pushState("data_inner"),24;case 23:return 27;case 24:return this.popState(),26;case 26:this.pushState("string");break;case 28:return"STR";case 29:return 24;case 30:return 26;case 31:return 43;case 32:return"COLON";case 33:return 44;case 34:return 28;case 35:return 45;case 36:return 46;case 37:return 48;case 38:return 50;case 39:return 47;case 40:return 41;case 41:return 49;case 42:return 42;case 44:return 35;case 45:return 36}},rules:[/^(?:%%(?!\{)[^\n]*)/i,/^(?:[^\}]%%[^\n]*)/i,/^(?:(\r?\n))/i,/^(?:(\r?\n))/i,/^(?:[\n\r]+)/i,/^(?:%%[^\n]*)/i,/^(?:title\b)/i,/^(?:accTitle\s*:\s*)/i,/^(?:(?!\n||)*[^\n]*)/i,/^(?:accDescr\s*:\s*)/i,/^(?:(?!\n||)*[^\n]*)/i,/^(?:accDescr\s*\{\s*)/i,/^(?:\{)/i,/^(?:[^\}]*)/i,/^(?:xychart-beta\b)/i,/^(?:(?:vertical|horizontal))/i,/^(?:x-axis\b)/i,/^(?:y-axis\b)/i,/^(?:\[)/i,/^(?:-->)/i,/^(?:line\b)/i,/^(?:bar\b)/i,/^(?:\[)/i,/^(?:[+-]?(?:\d+(?:\.\d+)?|\.\d+))/i,/^(?:\])/i,/^(?:(?:`\)                                    \{ this\.pushState\(md_string\); \}\n<md_string>\(\?:\(\?!`"\)\.\)\+                  \{ return MD_STR; \}\n<md_string>\(\?:`))/i,/^(?:["])/i,/^(?:["])/i,/^(?:[^"]*)/i,/^(?:\[)/i,/^(?:\])/i,/^(?:[A-Za-z]+)/i,/^(?::)/i,/^(?:\+)/i,/^(?:,)/i,/^(?:=)/i,/^(?:\*)/i,/^(?:#)/i,/^(?:[\_])/i,/^(?:\.)/i,/^(?:&)/i,/^(?:-)/i,/^(?:[0-9]+)/i,/^(?:\s+)/i,/^(?:;)/i,/^(?:$)/i],conditions:{data_inner:{rules:[0,1,4,5,6,7,9,11,14,15,16,17,20,21,23,24,25,26,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45],inclusive:!0},data:{rules:[0,1,3,4,5,6,7,9,11,14,15,16,17,20,21,22,25,26,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45],inclusive:!0},axis_band_data:{rules:[0,1,4,5,6,7,9,11,14,15,16,17,20,21,24,25,26,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45],inclusive:!0},axis_data:{rules:[0,1,2,4,5,6,7,9,11,14,15,16,17,18,19,20,21,23,25,26,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45],inclusive:!0},acc_descr_multiline:{rules:[12,13],inclusive:!1},acc_descr:{rules:[10],inclusive:!1},acc_title:{rules:[8],inclusive:!1},title:{rules:[],inclusive:!1},md_string:{rules:[],inclusive:!1},string:{rules:[27,28],inclusive:!1},INITIAL:{rules:[0,1,4,5,6,7,9,11,14,15,16,17,20,21,25,26,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45],inclusive:!0}}},Parser.prototype=D,D.Parser=Parser,new Parser}();function isBarPlot(t){return"bar"===t.type}function isBandAxisData(t){return"band"===t.type}function isLinearAxisData(t){return"linear"===t.type}r.parser=r;let TextDimensionCalculatorWithFont=class TextDimensionCalculatorWithFont{constructor(t){this.parentGroup=t}getMaxDimension(t,i){if(!this.parentGroup)return{width:t.reduce((t,i)=>Math.max(i.length,t),0)*i,height:i};let e={width:0,height:0},s=this.parentGroup.append("g").attr("visibility","hidden").attr("font-size",i);for(let a of t){let t=(0,n.c)(s,1,a),h=t?t.width:a.length*i,r=t?t.height:i;e.width=Math.max(e.width,h),e.height=Math.max(e.height,r)}return s.remove(),e}};let BaseAxis=class BaseAxis{constructor(t,i,e,s){this.axisConfig=t,this.title=i,this.textDimensionCalculator=e,this.axisThemeConfig=s,this.boundingRect={x:0,y:0,width:0,height:0},this.axisPosition="left",this.showTitle=!1,this.showLabel=!1,this.showTick=!1,this.showAxisLine=!1,this.outerPadding=0,this.titleTextHeight=0,this.labelTextHeight=0,this.range=[0,10],this.boundingRect={x:0,y:0,width:0,height:0},this.axisPosition="left"}setRange(t){this.range=t,"left"===this.axisPosition||"right"===this.axisPosition?this.boundingRect.height=t[1]-t[0]:this.boundingRect.width=t[1]-t[0],this.recalculateScale()}getRange(){return[this.range[0]+this.outerPadding,this.range[1]-this.outerPadding]}setAxisPosition(t){this.axisPosition=t,this.setRange(this.range)}getTickDistance(){let t=this.getRange();return Math.abs(t[0]-t[1])/this.getTickValues().length}getAxisOuterPadding(){return this.outerPadding}getLabelDimension(){return this.textDimensionCalculator.getMaxDimension(this.getTickValues().map(t=>t.toString()),this.axisConfig.labelFontSize)}recalculateOuterPaddingToDrawBar(){.7*this.getTickDistance()>2*this.outerPadding&&(this.outerPadding=Math.floor(.7*this.getTickDistance()/2)),this.recalculateScale()}calculateSpaceIfDrawnHorizontally(t){let i=t.height;if(this.axisConfig.showAxisLine&&i>this.axisConfig.axisLineWidth&&(i-=this.axisConfig.axisLineWidth,this.showAxisLine=!0),this.axisConfig.showLabel){let e=this.getLabelDimension(),s=.2*t.width;this.outerPadding=Math.min(e.width/2,s);let a=e.height+2*this.axisConfig.labelPadding;this.labelTextHeight=e.height,a<=i&&(i-=a,this.showLabel=!0)}if(this.axisConfig.showTick&&i>=this.axisConfig.tickLength&&(this.showTick=!0,i-=this.axisConfig.tickLength),this.axisConfig.showTitle&&this.title){let t=this.textDimensionCalculator.getMaxDimension([this.title],this.axisConfig.titleFontSize),e=t.height+2*this.axisConfig.titlePadding;this.titleTextHeight=t.height,e<=i&&(i-=e,this.showTitle=!0)}this.boundingRect.width=t.width,this.boundingRect.height=t.height-i}calculateSpaceIfDrawnVertical(t){let i=t.width;if(this.axisConfig.showAxisLine&&i>this.axisConfig.axisLineWidth&&(i-=this.axisConfig.axisLineWidth,this.showAxisLine=!0),this.axisConfig.showLabel){let e=this.getLabelDimension(),s=.2*t.height;this.outerPadding=Math.min(e.height/2,s);let a=e.width+2*this.axisConfig.labelPadding;a<=i&&(i-=a,this.showLabel=!0)}if(this.axisConfig.showTick&&i>=this.axisConfig.tickLength&&(this.showTick=!0,i-=this.axisConfig.tickLength),this.axisConfig.showTitle&&this.title){let t=this.textDimensionCalculator.getMaxDimension([this.title],this.axisConfig.titleFontSize),e=t.height+2*this.axisConfig.titlePadding;this.titleTextHeight=t.height,e<=i&&(i-=e,this.showTitle=!0)}this.boundingRect.width=t.width-i,this.boundingRect.height=t.height}calculateSpace(t){return"left"===this.axisPosition||"right"===this.axisPosition?this.calculateSpaceIfDrawnVertical(t):this.calculateSpaceIfDrawnHorizontally(t),this.recalculateScale(),{width:this.boundingRect.width,height:this.boundingRect.height}}setBoundingBoxXY(t){this.boundingRect.x=t.x,this.boundingRect.y=t.y}getDrawableElementsForLeftAxis(){let t=[];if(this.showAxisLine){let i=this.boundingRect.x+this.boundingRect.width-this.axisConfig.axisLineWidth/2;t.push({type:"path",groupTexts:["left-axis","axisl-line"],data:[{path:`M ${i},${this.boundingRect.y} L ${i},${this.boundingRect.y+this.boundingRect.height} `,strokeFill:this.axisThemeConfig.axisLineColor,strokeWidth:this.axisConfig.axisLineWidth}]})}if(this.showLabel&&t.push({type:"text",groupTexts:["left-axis","label"],data:this.getTickValues().map(t=>({text:t.toString(),x:this.boundingRect.x+this.boundingRect.width-(this.showLabel?this.axisConfig.labelPadding:0)-(this.showTick?this.axisConfig.tickLength:0)-(this.showAxisLine?this.axisConfig.axisLineWidth:0),y:this.getScaleValue(t),fill:this.axisThemeConfig.labelColor,fontSize:this.axisConfig.labelFontSize,rotation:0,verticalPos:"middle",horizontalPos:"right"}))}),this.showTick){let i=this.boundingRect.x+this.boundingRect.width-(this.showAxisLine?this.axisConfig.axisLineWidth:0);t.push({type:"path",groupTexts:["left-axis","ticks"],data:this.getTickValues().map(t=>({path:`M ${i},${this.getScaleValue(t)} L ${i-this.axisConfig.tickLength},${this.getScaleValue(t)}`,strokeFill:this.axisThemeConfig.tickColor,strokeWidth:this.axisConfig.tickWidth}))})}return this.showTitle&&t.push({type:"text",groupTexts:["left-axis","title"],data:[{text:this.title,x:this.boundingRect.x+this.axisConfig.titlePadding,y:this.boundingRect.y+this.boundingRect.height/2,fill:this.axisThemeConfig.titleColor,fontSize:this.axisConfig.titleFontSize,rotation:270,verticalPos:"top",horizontalPos:"center"}]}),t}getDrawableElementsForBottomAxis(){let t=[];if(this.showAxisLine){let i=this.boundingRect.y+this.axisConfig.axisLineWidth/2;t.push({type:"path",groupTexts:["bottom-axis","axis-line"],data:[{path:`M ${this.boundingRect.x},${i} L ${this.boundingRect.x+this.boundingRect.width},${i}`,strokeFill:this.axisThemeConfig.axisLineColor,strokeWidth:this.axisConfig.axisLineWidth}]})}if(this.showLabel&&t.push({type:"text",groupTexts:["bottom-axis","label"],data:this.getTickValues().map(t=>({text:t.toString(),x:this.getScaleValue(t),y:this.boundingRect.y+this.axisConfig.labelPadding+(this.showTick?this.axisConfig.tickLength:0)+(this.showAxisLine?this.axisConfig.axisLineWidth:0),fill:this.axisThemeConfig.labelColor,fontSize:this.axisConfig.labelFontSize,rotation:0,verticalPos:"top",horizontalPos:"center"}))}),this.showTick){let i=this.boundingRect.y+(this.showAxisLine?this.axisConfig.axisLineWidth:0);t.push({type:"path",groupTexts:["bottom-axis","ticks"],data:this.getTickValues().map(t=>({path:`M ${this.getScaleValue(t)},${i} L ${this.getScaleValue(t)},${i+this.axisConfig.tickLength}`,strokeFill:this.axisThemeConfig.tickColor,strokeWidth:this.axisConfig.tickWidth}))})}return this.showTitle&&t.push({type:"text",groupTexts:["bottom-axis","title"],data:[{text:this.title,x:this.range[0]+(this.range[1]-this.range[0])/2,y:this.boundingRect.y+this.boundingRect.height-this.axisConfig.titlePadding-this.titleTextHeight,fill:this.axisThemeConfig.titleColor,fontSize:this.axisConfig.titleFontSize,rotation:0,verticalPos:"top",horizontalPos:"center"}]}),t}getDrawableElementsForTopAxis(){let t=[];if(this.showAxisLine){let i=this.boundingRect.y+this.boundingRect.height-this.axisConfig.axisLineWidth/2;t.push({type:"path",groupTexts:["top-axis","axis-line"],data:[{path:`M ${this.boundingRect.x},${i} L ${this.boundingRect.x+this.boundingRect.width},${i}`,strokeFill:this.axisThemeConfig.axisLineColor,strokeWidth:this.axisConfig.axisLineWidth}]})}if(this.showLabel&&t.push({type:"text",groupTexts:["top-axis","label"],data:this.getTickValues().map(t=>({text:t.toString(),x:this.getScaleValue(t),y:this.boundingRect.y+(this.showTitle?this.titleTextHeight+2*this.axisConfig.titlePadding:0)+this.axisConfig.labelPadding,fill:this.axisThemeConfig.labelColor,fontSize:this.axisConfig.labelFontSize,rotation:0,verticalPos:"top",horizontalPos:"center"}))}),this.showTick){let i=this.boundingRect.y;t.push({type:"path",groupTexts:["top-axis","ticks"],data:this.getTickValues().map(t=>({path:`M ${this.getScaleValue(t)},${i+this.boundingRect.height-(this.showAxisLine?this.axisConfig.axisLineWidth:0)} L ${this.getScaleValue(t)},${i+this.boundingRect.height-this.axisConfig.tickLength-(this.showAxisLine?this.axisConfig.axisLineWidth:0)}`,strokeFill:this.axisThemeConfig.tickColor,strokeWidth:this.axisConfig.tickWidth}))})}return this.showTitle&&t.push({type:"text",groupTexts:["top-axis","title"],data:[{text:this.title,x:this.boundingRect.x+this.boundingRect.width/2,y:this.boundingRect.y+this.axisConfig.titlePadding,fill:this.axisThemeConfig.titleColor,fontSize:this.axisConfig.titleFontSize,rotation:0,verticalPos:"top",horizontalPos:"center"}]}),t}getDrawableElements(){if("left"===this.axisPosition)return this.getDrawableElementsForLeftAxis();if("right"===this.axisPosition)throw Error("Drawing of right axis is not implemented");return"bottom"===this.axisPosition?this.getDrawableElementsForBottomAxis():"top"===this.axisPosition?this.getDrawableElementsForTopAxis():[]}};let BandAxis=class BandAxis extends BaseAxis{constructor(t,i,e,s,a){super(t,s,a,i),this.categories=e,this.scale=(0,h.tiA)().domain(this.categories).range(this.getRange())}setRange(t){super.setRange(t)}recalculateScale(){this.scale=(0,h.tiA)().domain(this.categories).range(this.getRange()).paddingInner(1).paddingOuter(0).align(.5),a.l.trace("BandAxis axis final categories, range: ",this.categories,this.getRange())}getTickValues(){return this.categories}getScaleValue(t){return this.scale(t)||this.getRange()[0]}};let LinearAxis=class LinearAxis extends BaseAxis{constructor(t,i,e,s,a){super(t,s,a,i),this.domain=e,this.scale=(0,h.BYU)().domain(this.domain).range(this.getRange())}getTickValues(){return this.scale.ticks()}recalculateScale(){let t=[...this.domain];"left"===this.axisPosition&&t.reverse(),this.scale=(0,h.BYU)().domain(t).range(this.getRange())}getScaleValue(t){return this.scale(t)}};function getAxis(t,i,e,s){let a=new TextDimensionCalculatorWithFont(s);return isBandAxisData(t)?new BandAxis(i,e,t.categories,t.title,a):new LinearAxis(i,e,[t.min,t.max],t.title,a)}let ChartTitle=class ChartTitle{constructor(t,i,e,s){this.textDimensionCalculator=t,this.chartConfig=i,this.chartData=e,this.chartThemeConfig=s,this.boundingRect={x:0,y:0,width:0,height:0},this.showChartTitle=!1}setBoundingBoxXY(t){this.boundingRect.x=t.x,this.boundingRect.y=t.y}calculateSpace(t){let i=this.textDimensionCalculator.getMaxDimension([this.chartData.title],this.chartConfig.titleFontSize),e=Math.max(i.width,t.width),s=i.height+2*this.chartConfig.titlePadding;return i.width<=e&&i.height<=s&&this.chartConfig.showTitle&&this.chartData.title&&(this.boundingRect.width=e,this.boundingRect.height=s,this.showChartTitle=!0),{width:this.boundingRect.width,height:this.boundingRect.height}}getDrawableElements(){let t=[];return this.showChartTitle&&t.push({groupTexts:["chart-title"],type:"text",data:[{fontSize:this.chartConfig.titleFontSize,text:this.chartData.title,verticalPos:"middle",horizontalPos:"center",x:this.boundingRect.x+this.boundingRect.width/2,y:this.boundingRect.y+this.boundingRect.height/2,fill:this.chartThemeConfig.titleColor,rotation:0}]}),t}};function getChartTitleComponent(t,i,e,s){let a=new TextDimensionCalculatorWithFont(s);return new ChartTitle(a,t,i,e)}let LinePlot=class LinePlot{constructor(t,i,e,s,a){this.plotData=t,this.xAxis=i,this.yAxis=e,this.orientation=s,this.plotIndex=a}getDrawableElement(){let t;let i=this.plotData.data.map(t=>[this.xAxis.getScaleValue(t[0]),this.yAxis.getScaleValue(t[1])]);return(t="horizontal"===this.orientation?(0,h.jvg)().y(t=>t[0]).x(t=>t[1])(i):(0,h.jvg)().x(t=>t[0]).y(t=>t[1])(i))?[{groupTexts:["plot",`line-plot-${this.plotIndex}`],type:"path",data:[{path:t,strokeFill:this.plotData.strokeFill,strokeWidth:this.plotData.strokeWidth}]}]:[]}};let BarPlot=class BarPlot{constructor(t,i,e,s,a,n){this.barData=t,this.boundingRect=i,this.xAxis=e,this.yAxis=s,this.orientation=a,this.plotIndex=n}getDrawableElement(){let t=this.barData.data.map(t=>[this.xAxis.getScaleValue(t[0]),this.yAxis.getScaleValue(t[1])]),i=.95*Math.min(2*this.xAxis.getAxisOuterPadding(),this.xAxis.getTickDistance()),e=i/2;return"horizontal"===this.orientation?[{groupTexts:["plot",`bar-plot-${this.plotIndex}`],type:"rect",data:t.map(t=>({x:this.boundingRect.x,y:t[0]-e,height:i,width:t[1]-this.boundingRect.x,fill:this.barData.fill,strokeWidth:0,strokeFill:this.barData.fill}))}]:[{groupTexts:["plot",`bar-plot-${this.plotIndex}`],type:"rect",data:t.map(t=>({x:t[0]-e,y:t[1],width:i,height:this.boundingRect.y+this.boundingRect.height-t[1],fill:this.barData.fill,strokeWidth:0,strokeFill:this.barData.fill}))}]}};let BasePlot=class BasePlot{constructor(t,i,e){this.chartConfig=t,this.chartData=i,this.chartThemeConfig=e,this.boundingRect={x:0,y:0,width:0,height:0}}setAxes(t,i){this.xAxis=t,this.yAxis=i}setBoundingBoxXY(t){this.boundingRect.x=t.x,this.boundingRect.y=t.y}calculateSpace(t){return this.boundingRect.width=t.width,this.boundingRect.height=t.height,{width:this.boundingRect.width,height:this.boundingRect.height}}getDrawableElements(){if(!(this.xAxis&&this.yAxis))throw Error("Axes must be passed to render Plots");let t=[];for(let[i,e]of this.chartData.plots.entries())switch(e.type){case"line":{let s=new LinePlot(e,this.xAxis,this.yAxis,this.chartConfig.chartOrientation,i);t.push(...s.getDrawableElement())}break;case"bar":{let s=new BarPlot(e,this.boundingRect,this.xAxis,this.yAxis,this.chartConfig.chartOrientation,i);t.push(...s.getDrawableElement())}}return t}};function getPlotComponent(t,i,e){return new BasePlot(t,i,e)}let Orchestrator=class Orchestrator{constructor(t,i,e,s){this.chartConfig=t,this.chartData=i,this.componentStore={title:getChartTitleComponent(t,i,e,s),plot:getPlotComponent(t,i,e),xAxis:getAxis(i.xAxis,t.xAxis,{titleColor:e.xAxisTitleColor,labelColor:e.xAxisLabelColor,tickColor:e.xAxisTickColor,axisLineColor:e.xAxisLineColor},s),yAxis:getAxis(i.yAxis,t.yAxis,{titleColor:e.yAxisTitleColor,labelColor:e.yAxisLabelColor,tickColor:e.yAxisTickColor,axisLineColor:e.yAxisLineColor},s)}}calculateVerticalSpace(){let t=this.chartConfig.width,i=this.chartConfig.height,e=0,s=0,a=Math.floor(t*this.chartConfig.plotReservedSpacePercent/100),n=Math.floor(i*this.chartConfig.plotReservedSpacePercent/100),h=this.componentStore.plot.calculateSpace({width:a,height:n});t-=h.width,i-=h.height,s=(h=this.componentStore.title.calculateSpace({width:this.chartConfig.width,height:i})).height,i-=h.height,this.componentStore.xAxis.setAxisPosition("bottom"),h=this.componentStore.xAxis.calculateSpace({width:t,height:i}),i-=h.height,this.componentStore.yAxis.setAxisPosition("left"),e=(h=this.componentStore.yAxis.calculateSpace({width:t,height:i})).width,(t-=h.width)>0&&(a+=t,t=0),i>0&&(n+=i,i=0),this.componentStore.plot.calculateSpace({width:a,height:n}),this.componentStore.plot.setBoundingBoxXY({x:e,y:s}),this.componentStore.xAxis.setRange([e,e+a]),this.componentStore.xAxis.setBoundingBoxXY({x:e,y:s+n}),this.componentStore.yAxis.setRange([s,s+n]),this.componentStore.yAxis.setBoundingBoxXY({x:0,y:s}),this.chartData.plots.some(t=>isBarPlot(t))&&this.componentStore.xAxis.recalculateOuterPaddingToDrawBar()}calculateHorizonatalSpace(){let t=this.chartConfig.width,i=this.chartConfig.height,e=0,s=0,a=0,n=Math.floor(t*this.chartConfig.plotReservedSpacePercent/100),h=Math.floor(i*this.chartConfig.plotReservedSpacePercent/100),r=this.componentStore.plot.calculateSpace({width:n,height:h});t-=r.width,i-=r.height,e=(r=this.componentStore.title.calculateSpace({width:this.chartConfig.width,height:i})).height,i-=r.height,this.componentStore.xAxis.setAxisPosition("left"),r=this.componentStore.xAxis.calculateSpace({width:t,height:i}),t-=r.width,s=r.width,this.componentStore.yAxis.setAxisPosition("top"),r=this.componentStore.yAxis.calculateSpace({width:t,height:i}),i-=r.height,a=e+r.height,t>0&&(n+=t,t=0),i>0&&(h+=i,i=0),this.componentStore.plot.calculateSpace({width:n,height:h}),this.componentStore.plot.setBoundingBoxXY({x:s,y:a}),this.componentStore.yAxis.setRange([s,s+n]),this.componentStore.yAxis.setBoundingBoxXY({x:s,y:e}),this.componentStore.xAxis.setRange([a,a+h]),this.componentStore.xAxis.setBoundingBoxXY({x:0,y:a}),this.chartData.plots.some(t=>isBarPlot(t))&&this.componentStore.xAxis.recalculateOuterPaddingToDrawBar()}calculateSpace(){"horizontal"===this.chartConfig.chartOrientation?this.calculateHorizonatalSpace():this.calculateVerticalSpace()}getDrawableElement(){this.calculateSpace();let t=[];for(let i of(this.componentStore.plot.setAxes(this.componentStore.xAxis,this.componentStore.yAxis),Object.values(this.componentStore)))t.push(...i.getDrawableElements());return t}};let XYChartBuilder=class XYChartBuilder{static build(t,i,e,s){let a=new Orchestrator(t,i,e,s);return a.getDrawableElement()}};let l=0,c=getChartDefaultConfig(),g=getChartDefaultThemeConfig(),u=getChartDefaultData(),x=g.plotColorPalette.split(",").map(t=>t.trim()),d=!1,p=!1;function getChartDefaultThemeConfig(){let t=(0,a.D)(),i=(0,a.E)();return(0,a.B)(t.xyChart,i.themeVariables.xyChart)}function getChartDefaultConfig(){let t=(0,a.E)();return(0,a.B)(a.A.xyChart,t.xyChart)}function getChartDefaultData(){return{yAxis:{type:"linear",title:"",min:1/0,max:-1/0},xAxis:{type:"band",title:"",categories:[]},title:"",plots:[]}}function textSanitizer(t){let i=(0,a.E)();return(0,a.d)(t.trim(),i)}function setTmpSVGG(t){s=t}function setOrientation(t){"horizontal"===t?c.chartOrientation="horizontal":c.chartOrientation="vertical"}function setXAxisTitle(t){u.xAxis.title=textSanitizer(t.text)}function setXAxisRangeData(t,i){u.xAxis={type:"linear",title:u.xAxis.title,min:t,max:i},d=!0}function setXAxisBand(t){u.xAxis={type:"band",title:u.xAxis.title,categories:t.map(t=>textSanitizer(t.text))},d=!0}function setYAxisTitle(t){u.yAxis.title=textSanitizer(t.text)}function setYAxisRangeData(t,i){u.yAxis={type:"linear",title:u.yAxis.title,min:t,max:i},p=!0}function setYAxisRangeFromPlotData(t){let i=isLinearAxisData(u.yAxis)?u.yAxis.min:1/0,e=isLinearAxisData(u.yAxis)?u.yAxis.max:-1/0;u.yAxis={type:"linear",title:u.yAxis.title,min:Math.min(i,Math.min(...t)),max:Math.max(e,Math.max(...t))}}function transformDataWithoutCategory(t){let i=[];if(0===t.length)return i;if(!d){let i=isLinearAxisData(u.xAxis)?u.xAxis.min:1/0,e=isLinearAxisData(u.xAxis)?u.xAxis.max:-1/0;setXAxisRangeData(Math.min(i,1),Math.max(e,t.length))}if(p||setYAxisRangeFromPlotData(t),isBandAxisData(u.xAxis)&&(i=u.xAxis.categories.map((i,e)=>[i,t[e]])),isLinearAxisData(u.xAxis)){let e=u.xAxis.min,s=u.xAxis.max,a=(s-e+1)/t.length,n=[];for(let t=e;t<=s;t+=a)n.push(`${t}`);i=n.map((i,e)=>[i,t[e]])}return i}function getPlotColorFromPalette(t){return x[0===t?0:t%x.length]}function setLineData(t,i){let e=transformDataWithoutCategory(i);u.plots.push({type:"line",strokeFill:getPlotColorFromPalette(l),strokeWidth:2,data:e}),l++}function setBarData(t,i){let e=transformDataWithoutCategory(i);u.plots.push({type:"bar",fill:getPlotColorFromPalette(l),data:e}),l++}function getDrawableElem(){if(0===u.plots.length)throw Error("No Plot to render, please provide a plot with some data");return u.title=(0,a.r)(),XYChartBuilder.build(c,u,g,s)}function getChartThemeConfig(){return g}function getChartConfig(){return c}let f={getDrawableElem,clear:function(){(0,a.t)(),l=0,c=getChartDefaultConfig(),u=getChartDefaultData(),x=(g=getChartDefaultThemeConfig()).plotColorPalette.split(",").map(t=>t.trim()),d=!1,p=!1},setAccTitle:a.s,getAccTitle:a.g,setDiagramTitle:a.q,getDiagramTitle:a.r,getAccDescription:a.a,setAccDescription:a.b,setOrientation,setXAxisTitle,setXAxisRangeData,setXAxisBand,setYAxisTitle,setYAxisRangeData,setLineData,setBarData,setTmpSVGG,getChartThemeConfig,getChartConfig},y={parser:r,db:f,renderer:{draw:(t,i,e,s)=>{let n=s.db,h=n.getChartThemeConfig(),r=n.getChartConfig();function getDominantBaseLine(t){return"top"===t?"text-before-edge":"middle"}function getTextAnchor(t){return"left"===t?"start":"right"===t?"end":"middle"}function getTextTransformation(t){return`translate(${t.x}, ${t.y}) rotate(${t.rotation||0})`}a.l.debug("Rendering xychart chart\n"+t);let l=(0,a.z)(i),c=l.append("g").attr("class","main"),g=c.append("rect").attr("width",r.width).attr("height",r.height).attr("class","background");(0,a.i)(l,r.height,r.width,!0),l.attr("viewBox",`0 0 ${r.width} ${r.height}`),g.attr("fill",h.backgroundColor),n.setTmpSVGG(l.append("g").attr("class","mermaid-tmp-group"));let u=n.getDrawableElem(),x={};function getGroup(t){let i=c,e="";for(let[s]of t.entries()){let a=c;s>0&&x[e]&&(a=x[e]),e+=t[s],(i=x[e])||(i=x[e]=a.append("g").attr("class",t[s]))}return i}for(let t of u){if(0===t.data.length)continue;let i=getGroup(t.groupTexts);switch(t.type){case"rect":i.selectAll("rect").data(t.data).enter().append("rect").attr("x",t=>t.x).attr("y",t=>t.y).attr("width",t=>t.width).attr("height",t=>t.height).attr("fill",t=>t.fill).attr("stroke",t=>t.strokeFill).attr("stroke-width",t=>t.strokeWidth);break;case"text":i.selectAll("text").data(t.data).enter().append("text").attr("x",0).attr("y",0).attr("fill",t=>t.fill).attr("font-size",t=>t.fontSize).attr("dominant-baseline",t=>getDominantBaseLine(t.verticalPos)).attr("text-anchor",t=>getTextAnchor(t.horizontalPos)).attr("transform",t=>getTextTransformation(t)).text(t=>t.text);break;case"path":i.selectAll("path").data(t.data).enter().append("path").attr("d",t=>t.path).attr("fill",t=>t.fill?t.fill:"none").attr("stroke",t=>t.strokeFill).attr("stroke-width",t=>t.strokeWidth)}}}}}}}]);