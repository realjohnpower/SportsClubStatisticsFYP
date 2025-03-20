var svg;
var data;
var x;
var y;

var margin;
var width;
var height;
var xAxis;
var yAxis;
var yAxisTitle;

function drawTeamStatisticsChart(data,yAxisLabel) {
    data=JSON.parse(data);


    margin = {top: 30, right: 30, bottom: 70, left: 60},
        width = 460 - margin.left - margin.right,
        height = 400 - margin.top - margin.bottom;


// append the svg object to the body of the page
     svg = d3.select("#team-event-stats-barchart")
        .append("svg")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
        .append("g")
        .attr("transform",
            "translate(" + margin.left + "," + margin.top + ")");

// Initialize the X axis
      x = d3.scaleBand()
        .range([ 0, width ])
        .padding(0.2);
     xAxis = svg.append("g")
        .attr("transform", "translate(0," + height + ")")


// Initialize the Y axis
     y = d3.scaleLinear()
        .range([ height, 0]);
     yAxis = svg.append("g")
        .attr("class", "myYaxis")

    yAxisTitle = svg.append("text")
        .attr("class", "y-axis-title")
        .attr("transform", "rotate(-90)")
        .attr("x", -height / 2)
        .attr("y", -50)
        .attr("text-anchor", "middle")
        .style("font-size", "14px")
        .text(yAxisLabel); // Set initial label dynamically
    updateTeamEventStatsChart(data);
}
function updateTeamEventStatsChart(data, yAxisLabel){

    // X axis
    x.domain(data.map(function(d) { return d.name; }))
    xAxis.transition().duration(1000).call(d3.axisBottom(x))

    // Add Y axis
    y.domain([0, d3.max(data, function(d) { return d.value }) ]);
    yAxis.transition().duration(1000).call(d3.axisLeft(y));

    // variable u: map data to existing bars
    var u = svg.selectAll("rect")
        .data(data)

    // update bars
    u
        .enter()
        .append("rect")
        .merge(u)
        .transition()
        .duration(1000)
        .attr("x", function(d) { return x(d.name); })
        .attr("y", function(d) { return y(d.value); })
        .attr("width", x.bandwidth())
        .attr("height", function(d) { return height - y(d.value); })
        .attr("fill", "#69b3a2")


    yAxisTitle.text(yAxisLabel)


}

function updateTeamStatsChartData(data,yAxisLabel){
    let jsonString= data;
    var parsedData = JSON.parse(jsonString)

    updateTeamEventStatsChart(parsedData,yAxisLabel);
}
