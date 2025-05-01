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
var chartTitle;

function drawTeamStatisticsChart(data,yAxisLabel) {
    data=JSON.parse(data);


    // Setting the dimensions of the graph
    margin = {top: 150, right: 30, bottom: 70, left: 60},
        width = 1000 - margin.left - margin.right,
        height = 600 - margin.top - margin.bottom;


// Appending svg element to the #team-event-stats-barchart div
     svg = d3.select("#team-event-stats-barchart")
        .append("svg")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
        .append("g")
        .attr("transform",
            "translate(" + margin.left + "," + margin.top + ")");

// Creating x-axis scales for the bar graph
      x = d3.scaleBand()
        .range([ 0, width ])
        .padding(0.2);

     xAxis = svg.append("g")
        .attr("transform", "translate(0," + height + ")")


// Creating the Y axis for the bar graph
     y = d3.scaleLinear()
        .range([ height, 0])

    yAxis = svg.append("g")

    // Adding the chart title to the bar graph
    chartTitle =svg.append("text")
        .attr("x", width / 2)
        .attr("y", -20)
        .attr("text-anchor", "middle")
        .style("font-size", "18px")
        .style("font-weight", "bold")
        .style("font-family","sans-serif")
        .text("Top Players By "+yAxisLabel+" In This Session.");

    // Adding X-axis label to the bar graph
    xAxisLabel = svg.append("text")
        .attr("x", width / 2)
        .attr("y", height + 40)
        .attr("text-anchor", "middle")
        .style("font-size", "14px")
        .style("font-family","sans-serif")
        .text("Players");
    // Adding Y-axis label to the Bar Graph
    yAxisTitle = svg.append("text")
        .attr("class", "y-axis-title")
        .attr("transform", "rotate(-90)")
        .attr("x", -height / 2)
        .attr("y", -50)
        .attr("text-anchor", "middle")
        .style("font-size", "14px")
        .style("font-family","sans-serif")

    updateTeamEventStatsChart(data, "Max BPM Value");
}
function updateTeamEventStatsChart(data, yAxisLabel){
    const tooltip = d3.select("body").append("div")
        .attr("class", "tooltip")

    // Updating the X axis on the bar graph
    x.domain(data.map(function(d) { return d.name; }))
    xAxis.transition().duration(1000).call(d3.axisBottom(x))

    // Updating the  Y axis on the bar graph
    y.domain([0, d3.max(data, function(d) { return d.value }) ]);
    yAxis.transition().duration(1000).call(d3.axisLeft(y));

    // Drawing bars on the graph according to the data passed in
    var bars = svg.selectAll("rect")
        .data(data)


        bars.join("rect")
        .on("mouseover", (event, d) => {
            tooltip.style("opacity", 1)
                .html(`<strong>Player: ${d.name}</strong><br>Value: ${d.value}`)
                .style("left", (event.pageX + 15) + "px")
                .style("top", (event.pageY + 15) + "px");
        })
        .on("mouseout", () => tooltip.style("opacity", 0))
        .transition()
        .duration(1000)
        .attr("x", function(d) { return x(d.name); })
        .attr("y", function(d) { return y(d.value); })
        .attr("width", x.bandwidth())
        .attr("height", function(d) { return height - y(d.value); })
            .attr("fill", "#1f77b4")

    //Updating the chart title on the bar graph
    chartTitle.text("Top Players By "+yAxisLabel+" In This Session.");
    //Updating Y axis title on the bar graph
    yAxisTitle.text(yAxisLabel)


}

function updateTeamStatsChartData(data,yAxisLabel){
    let jsonString= data;
    var parsedData = JSON.parse(jsonString)

    updateTeamEventStatsChart(parsedData,yAxisLabel);
}
