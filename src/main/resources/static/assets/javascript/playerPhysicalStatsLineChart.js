var margin,
    width,
    height,
    svg,
    x,
    y,
    line,
    xAxisGroup,
    yAxisGroup,
    chartBody,
    path,
    circlesGroup


function drawPlayerPhysicalStatsLineChart(stringData) {
    data = JSON.parse(stringData, (key, value) => {
        if (key === "date") {
            return new Date(value);  // Using trim() to be safe
        }
        return value;
    });
        margin = {top: 60, right: 50, bottom: 50, left: 60},
        width = 800 - margin.left - margin.right,
        height = 400 - margin.top - margin.bottom;


// Append an SVG element to the #chart div
         svg = d3.select("#physical-stats-line-chart")
        .append("svg")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
        .append("g")
        .attr("transform", `translate(${margin.left},${margin.top})`);

// Define x and y scales
        x = d3.scaleTime().range([0, width]);
        y = d3.scaleLinear().range([height, 0]);

// Define the line generator
        line = d3.line()
        .x(function(d) { return x(d.date); })
        .y(function(d) { return y(d.value); })
        .curve(d3.curveMonotoneX);// Smooth curve

// Create groups for axes
        xAxisGroup = svg.append("g")
        .attr("transform", `translate(0,${height})`);
        yAxisGroup = svg.append("g");



// Group for the line and circles, with clip path applied
        chartBody = svg.append("g")
        .attr("clip-path", "url(#clip)");

// Append the path element for the line
        path = chartBody.append("path")
        .attr("fill", "none")
        .attr("stroke", "steelblue")
        .attr("stroke-width", 2);

// Group for the circles
     circlesGroup = chartBody.append("g");
     updateLineChart(data);
}
// Function to update the chart with new data
function updateLineChart(data) {
    // Assume data is an array of objects with properties: date (JavaScript Date) and value (number)

    // Update the scales' domains
    x.domain(d3.extent(data, d => d.date));
    y.domain([0, d3.max(data, d => d.value)]).nice();

    const tickDates = data.map(d => d.date);
    // Update axes
    xAxisGroup.transition().duration(1000)
        .call(d3.axisBottom(x).tickValues(tickDates).tickFormat(d3.timeFormat("%d-%m-%Y")));
    yAxisGroup.transition().duration(1000)
        .call(d3.axisLeft(y));

    // Update the line path
    path.datum(data)
        .transition()
        .duration(1000)
        .attr("d", line);

    // Data join for circles
    var circles = circlesGroup.selectAll("circle")
        .data(data);

    // Remove any circles that are no longer needed
    circles.exit().transition().duration(1000)
        .attr("r", 0)
        .remove();

    // Update existing circles
    circles.transition().duration(1000)
        .attr("cx", function(d) { return x(d.date); })
        .attr("cy", function(d) { return y(d.value); })
        .attr("r", 4);

    // Enter new circles
    circles.enter().append("circle")
        .attr("cx", function(d) { return x(d.date); })
        .attr("cy", function(d) { return y(d.value); })
        .attr("r", 0)
        .attr("fill", "red")
        .transition().duration(1000)
        .attr("r", 4);
}
function updateChartData(data){
    let jsonString= data;
    var parsedData = JSON.parse(jsonString, (key, value) => {
        if (key === "date") {
            return new Date(value);  // Using trim() to be safe
        }
        return value;
    });
    updateLineChart(parsedData);
}