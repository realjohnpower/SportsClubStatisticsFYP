var margin,
    width,
    height,
    svg,
    x,
    y,
    line,
    xAxisGroup,
    yAxisGroup,
    path,
    circlesGroup,
    yAxisLabel,
    xAxisLabel,
    chartTitle;

const lineGraphTooltip = d3.select("body").append("div")
    .attr("class", "tooltip")



function drawPlayerPhysicalStatsLineChart(stringData, yAxisText, playerName ) {
    //Parsing the data to JSON format
    data = JSON.parse(stringData, (key, value) => {
        if (key === "date") {
            return new Date(value);
        }
        return value;
    });
        margin = {top: 60, right: 50, bottom: 50, left: 60},
        width = 800 - margin.left - margin.right,
        height = 400 - margin.top - margin.bottom;

// Appending an SVG element to the physical-stats-line-chart div
         svg = d3.select("#physical-stats-line-chart")
        .append("svg")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
        .append("g")
        .attr("transform", `translate(${margin.left},${margin.top})`);

// Creating the x and y-axis scales for the line graph
        x = d3.scaleTime().range([0, width]);
        y = d3.scaleLinear().range([height, 0]);

// Appending groups for x and y axes on the line graph
        xAxisGroup = svg.append("g")
        .attr("transform", `translate(0,${height})`);
        yAxisGroup = svg.append("g");

// Appending a group element for the line on the line graph
      linePathGroup = svg.append("g")

// Appending the SVG path element for the line
        path = linePathGroup.append("g")
            .append("path")
        .attr("fill", "none")
        .attr("stroke", "steelblue")
        .attr("stroke-width", 2);

// Creating group for the circles on the line graph
     circlesGroup = svg.append("g");

    // Adding the x-axis label to the line graph
    xAxisLabel = svg.append("text")
        .attr("x", width / 2)
        .attr("y", height + 40)
        .attr("text-anchor", "middle")
        .style("font-size", "14px")
        .text("Date Recorded");

    // Adding the y-axis label to the line graph
    yAxisLabel = svg.append("text")
        .attr("transform", "rotate(-90)")
        .attr("x", -height / 2)
        .attr("y", -50)
        .attr("text-anchor", "middle")
        .style("font-size", "14px")

// Adding the chart title to the line graph
   chartTitle= svg.append("text")
        .attr("x", width / 2)
        .attr("y", -20)
        .attr("text-anchor", "middle")
        .style("font-size", "18px")
        .style("font-weight", "bold")

     updateLineChart(data, yAxisText, playerName);
}
// Function to draw the line and circles of the line graph according to the data passed in
function updateLineChart(data, yAxisText, playerName) {

    // Updating the x and y-axis domains on the line graph according to the data passed in
    x.domain(d3.extent(data, d => d.date));
    y.domain([0, d3.max(data, d => d.value)]).nice();

    const tickDates = data.map(d => d.date);

    const tooltipTimeFormat=d3.timeFormat("%d %b %Y");

    // Updating the X and Y axes on the line graph according to the data passed in
    xAxisGroup.transition().duration(1000)
        .call(d3.axisBottom(x).tickValues(tickDates).tickFormat(d3.timeFormat("%d-%m-%Y")));
    yAxisGroup.transition().duration(1000)
        .call(d3.axisLeft(y));

    // Creating a function that returns an SVG path element which describes the shape of the line
    line = d3.line()
        .x((d) => { return x(d.date); })
        .y((d) => { return y(d.value); })
    // Updating the line on the line graph according to the line generated based on the data.
    path.datum(data)
        .transition()
        .duration(1000)
        .attr("d", line);

    // Binding the data to all of the circle elements on the line graph.
    var circles = circlesGroup.selectAll("circle")
        .data(data);

    // Updating the circles positions on the line graph according to the data passed in
    circles.join("circle")
        .attr("cx", (d)=> { return x(d.date); })
        .attr("cy", (d)=> { return y(d.value); })
        .attr("r", 0)
        .attr("fill", "red")
        .attr("r", 4)
        .on("mouseover", (event, d) => {
            lineGraphTooltip.style("opacity", 1)
                .html(`<strong>Date: </strong>${tooltipTimeFormat(d.date)}<br><strong>Value:</strong> ${d.value}`)
                .style("left", (event.pageX + 15) + "px")
                .style("top", (event.pageY + 15) + "px");
        })
        .on("mouseout", () => lineGraphTooltip.style("opacity", 0))
        .transition()
        .duration(1000)
    //Updating the chart title and the y-axis label on the line graph.
    chartTitle.text(playerName+" "+yAxisText+" Trends Over Time");
    yAxisLabel.text(yAxisText);

}
function updateChartData(data, yAxisText, playerName){
   //Parsing the new data into JSON format
    let jsonString= data;
    var parsedData = JSON.parse(jsonString, (key, value) => {
        if (key === "date") {
            return new Date(value);
        }
        return value;
    });
    //Updating the line graph with the new data.
    updateLineChart(parsedData, yAxisText, playerName);
}