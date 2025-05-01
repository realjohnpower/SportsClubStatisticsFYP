var svg, x, y, margin, width, height, yAxisTitle, yAxisGroup


const tooltip = d3.select("body").append("div")
    .attr("class", "tooltip")


function drawGenderChart(data, yAxisLabel) {
    // Data is parsed into JSON format
    data= JSON.parse(data);

    var maxValue = d3.max(data, (d)=> { return d.value; });

    //Setting the dimensions for the bar graph
    margin = { top: 60, right: 30, bottom: 70, left: 60 };
    width = 600 - margin.left - margin.right;
    height = 400 - margin.top - margin.bottom;

    // Adding an SVG element to gender barchart div
    svg = d3.select("#gender-barchart")
        .append("svg")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
        .append("g")
        .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    // Creating the band scale for the x-axis
    x = d3.scaleBand()
        .range([0, width])
        .domain(data.map((d)=> { return d.gender; }))
        .padding(0.2);
    // Adding the x-axis to the bar graph.
        svg.append("g")
        .attr("transform", "translate(0," + height + ")")
        .call(d3.axisBottom(x));

    // Creating the linear scale for the y-axis
    y = d3.scaleLinear()
        .domain([0, maxValue])
        .range([height, 0]);
    // Adding the y-Axis to the bar graph
    yAxisGroup=svg.append("g")
        .call(d3.axisLeft(y));

    //Adding the chart title to the graph
    svg.append("text")
        .attr("x", width / 2)
        .attr("y", -20)
        .attr("text-anchor", "middle")
        .style("font-size", "16px")
        .style("font-weight", "bold")
        .text("Members Distribution by Gender");

    // Adding the x-axis label to the graph
    xAxisTitle = svg.append("text")
        .attr("x", width / 2)
        .attr("y", height + 40)
        .attr("text-anchor", "middle")
        .style("font-size", "14px")
        .text("Gender");

    // Adding the y-axis label to the graph
    yAxisTitle = svg.append("text")
        .attr("transform", "rotate(-90)")
        .attr("x", -height / 2)
        .attr("y", -50)
        .attr("text-anchor", "middle")
        .style("font-size", "14px")

    // Calling the "updateGenderChart" function to draw the bars on the graph.
    updateGenderChart(data, yAxisLabel);
}

function updateGenderChart(data, yAxisLabel) {


    var maxValue = d3.max(data, (d)=> { return d.value; });

    // Updating the y-axis domain of the bar graph
    y.domain([0,maxValue])

    // Updating the values on the bar graph's y-axis scale
    yAxisGroup.transition()
              .duration(1000)
        .call(d3.axisLeft(y));

    // Binding the data to the SVG rectangle elements
     var bars =svg.selectAll("rect")
         .data(data);


    // Drawing bars according to the data passed in
       bars.join("rect")
        .on("mouseover", (event, d) => {
            tooltip.style("opacity", 1)
                .html(`<strong>Gender: ${d.gender}</strong><br>New Members Total: ${d.value}`)
                .style("left", (event.pageX + 15) + "px")
                .style("top", (event.pageY + 15) + "px");
        })
        .on("mouseout", () => tooltip.style("opacity", 0))
        .transition()
        .duration(1000)
        .attr("x", (d) => { return x(d.gender); })
        .attr("y", (d) => { return y(d.value); })
        .attr("width", x.bandwidth())
        .attr("height", (d) => { return height - y(d.value); })
        .attr("fill", "#1f77b4")

    // Updating the y-axis title on the bar graph.
    yAxisTitle.text(yAxisLabel);

}

function updateGenderChartData(data,yAxisLabel){
    //Parsing the data into JSON format
    let jsonString= data;
    var parsedData = JSON.parse(jsonString)
    //Updating the line chart with new data.
    updateGenderChart(parsedData,yAxisLabel);
}
