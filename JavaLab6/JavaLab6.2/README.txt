GRAPH DRAWING TOOL

*Update for the bonus part:
I made it work, the "Format" button now draws the graph in a prettier way after detecting intersections and trying to
minimize them
Note: I have also added screenshots to illustrate the functionality

How-to:
1. Clicking on the canvas creates a vertex marked with a number
2. To add an edge, simply type the corresponding numbers for its ends and click "Add edge"
3. You can reset your graph anytime and start all over, or you can even save it at a specific path
(In progress: The "format graph" button will try and repaint your graph in a "prettier" way, based on some
research I've done (Isabel F. Cruz p.59) (with respect to computational efficiency), as it follows:
-place the "most important" vertex in the middle
-layout of the graph generated according to a prespecified set of aesthetic criteria
-aesthetic criteria embodied in an algorithm as optimization goals
E.g. minimization of crossings / minimization of area
Note: keep in mind that most problems regarding graph drawing are NP-hard)
Resources available in the PDFs.