grammar StlAscii;

@header {
    package rocks.spiffy.stl;
}

Solid: 'solid';
EndSolid: 'endsolid';

FacetNormal: 'facet normal';
EndFacetNormal: 'endfacet';

OuterLoop: 'outer loop';
EndOuterLoop: 'endloop';

Vertex: 'vertex';

ExpNumber: DecimalNumber Exponent?;
Name: ('A'..'Z' | 'a'..'z' | '_' | '0' .. '9')+;
//ExpNumber: '-1';

fragment Exponent: 'e' RealNumber;
fragment DecimalNumber: Sign? ('0' .. '9')+ ('.' ('0' .. '9')+)?;
fragment RealNumber: Sign? ('0' .. '9')+;
fragment Sign: '-';
WS: (' ' | '\t' | '\n' | '\r' )+;
Space: ' ';
fragment NL:  '\r'? '\n';


solid: Solid WS name=Name WS
            facets WS
       EndSolid;

facets: (facet WS)* facet WS?;

//facet: 'facet normal -1 0 0' WS
//            outerLoop WS
//       'endfacet';

facet: FacetNormal WS normalX=ExpNumber WS normalX=ExpNumber WS normalX=ExpNumber WS
           outerLoop WS
       EndFacetNormal;

outerLoop: OuterLoop WS
             Vertex WS vertexA_X=ExpNumber WS vertexA_Y=ExpNumber WS vertexA_Z=ExpNumber WS
             Vertex WS vertexB_Y=ExpNumber WS vertexB_Y=ExpNumber WS vertexB_Z=ExpNumber WS
             Vertex WS vertexC_X=ExpNumber WS vertexC_Y=ExpNumber WS vertexC_Z=ExpNumber WS
           EndOuterLoop;