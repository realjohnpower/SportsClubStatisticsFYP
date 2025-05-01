


const ageGrouptooltip = d3.select("body").append("div")
    .attr("class", "tooltip")


function displayAgeGroupBarChart(data) {
    data=JSON.parse(data);


    // Setting up dimensions for the bar chart graph
    var margin = {top: 60, right: 30, bottom: 70, left: 60},
        width = 600 - margin.left - margin.right,
        height = 400 - margin.top - margin.bottom;


    // D3
// Appending svg to the gender-age-barchart div
    var svg = d3.select("#gender-age-barchart")
        .append("svg")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
        .append("g")
        .attr("transform", "translate(" + margin.left + "," + margin.top + ")");


// Variables for the age groups and genders
    var ageGroups = data.map(d => d.group);
    var gendersSubGroups = ["Male", "Female"];

// Creating the x-axis scale for the age group
    var x = d3.scaleBand()
        .domain(ageGroups)
        .range([0, width])
        .padding(0.2);
// D3 (no date)
// Basic grouped barplot in d3.js
// d3 version 6
// https://d3-graph-gallery.com/graph/barplot_grouped_basicWide.html [accessed 18 February 2024]

// Creating sub-scales for each gender within each group
    var xSubgroup = d3.scaleBand()
        .domain(gendersSubGroups)
        .range([0, x.bandwidth()])
        .padding(0.05);

    // Creating color scale for each gender
    var color = d3.scaleOrdinal()
        .domain(gendersSubGroups)
        .range(["#1f77b4", "#ff33c4"]); // Blue for male, Pink for female

// Creating the y-axis scale
    var maxValue = d3.max(data, d => Math.max(d.Male, d.Female));
    var y = d3.scaleLinear()
        .domain([0, maxValue])
        .range([height, 0]);




// Adding x-axis to the bar graph
    svg.append("g")
        .attr("transform", "translate(0," + height + ")")
        .call(d3.axisBottom(x));

// Adding the y-axis to the bar graph
    svg.append("g")
        .call(d3.axisLeft(y));

// Drawing the bars for the bar according to the data passed in
    svg.append("g")
        .selectAll("g")
        .data(data)
        .join("g")
        .attr("transform", d => "translate(" + x(d.group) + ",0)")
        // D3 (no date)
        // Basic grouped barplot in d3.js
        // d3 version 6
        // https://d3-graph-gallery.com/graph/barplot_grouped_basicWide.html [accessed 18 February 2024]
        .selectAll("rect")
        .data(d => gendersSubGroups.map(gender => ({key: gender, value: d[gender]})))
        .join("rect")
        .attr("x", d => xSubgroup(d.key))
        .attr("y", d => y(d.value))
        .attr("width", xSubgroup.bandwidth())
        .attr("height", d => height - y(d.value))
        .attr("fill", d => color(d.key))
        .on("mouseover", (event, d) => {
        ageGrouptooltip.style("opacity", 1)
            .html(`<strong>Total: </strong> ${d.value} <br> <strong> Gender: </strong> ${d.key} `)
            .style("left", (event.pageX + 15) + "px")
            .style("top", (event.pageY + 15) + "px");
    })
        .on("mouseout", () => ageGrouptooltip.style("opacity", 0))

    // Adding the chart title to the bar graph
    svg.append("text")
        .attr("x", width / 2)
        .attr("y", -20)
        .attr("text-anchor", "middle")
        .style("font-size", "16px")
        .style("font-weight", "bold")
        .text("Age Group Distribution by Gender");

    //Adding the x-axis label to the bar graph
    svg.append("text")
        .attr("x", width / 2)
        .attr("y", height + 50)
        .attr("text-anchor", "middle")
        .style("font-size", "14px")
        .text("Age Groups");

    //Adding the y-axis label to the bar graph
    svg.append("text")
        .attr("transform", "rotate(-90)")
        .attr("x", -height / 2)
        .attr("y", -50)
        .attr("text-anchor", "middle")
        .style("font-size", "14px")
        .text("Number of Users");

// Adding legend for each gender located in top right corner of the bar graph
    var legend = svg.append("g")
        .attr("transform", "translate(" + (width - 100) + ", 0)");

    legend.append("rect")
        .attr("x", 0)
        .attr("y",0)
        .attr("width", 15)
        .attr("height", 15)
        .attr("fill", "#1f77b4")

    legend.append("text")
        .attr("x", 20)
        .attr("y", 12)
        .text("Male")
        .attr("font-size", "12px")
        .attr("alignment-baseline", "middle");

    legend.append("rect")
        .attr("x", 0)
        .attr("y",20)
        .attr("width", 15)
        .attr("height", 15)
        .attr("fill", "#ff33c4")

    legend.append("text")
        .attr("x", 20)
        .attr("y", 32)
        .text("Female")
        .attr("font-size", "12px")
        .attr("alignment-baseline", "middle");


}