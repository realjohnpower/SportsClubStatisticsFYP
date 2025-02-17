var svg;
var data;
var x;
var y;

var margin;
var width;
var height;

    function drawGenderChart(maleCount, femaleCount) {
        // Step 1: Find the maximum value from the data array
        var data = [{group: "Male", value: maleCount},
            {group: "Female", value: femaleCount}];
        var maxValue = d3.max(data, function(d) { return d.value; });

            margin = {top: 30, right: 30, bottom: 70, left: 60},
            width = 460 - margin.left - margin.right,
            height = 400 - margin.top - margin.bottom;

// append the svg object to the body of the page
        svg = d3.select("#gender-barchart")
            .append("svg")
            .attr("width", width + margin.left + margin.right)
            .attr("height", height + margin.top + margin.bottom)
            .append("g")
            .attr("transform",
                "translate(" + margin.left + "," + margin.top + ")");

        var data = [{group: "Male", value: maleCount},
            {group: "Female", value: femaleCount}];

           x = d3.scaleBand()
            .range([0, width])
            .domain(data.map(function (d) {
                return d.group;
            }))
            .padding(0.2);
        svg.append("g")
            .attr("transform", "translate(0," + height + ")")
            .call(d3.axisBottom(x))

// Add Y axis
           y = d3.scaleLinear()
            .domain([0, maxValue])
            .range([height, 0]);
        svg.append("g")
            .attr("class", "myYaxis")
            .call(d3.axisLeft(y));
updateGenderChart(maleCount, femaleCount);
    }
function updateGenderChart(maleCount, femaleCount){
    var data = [{group: "Male", value: maleCount},
        {group: "Female", value: femaleCount}];



    var u = svg.selectAll('rect')
        .data(data)

    u.enter()
        .append("rect")
        .merge(u)
        .transition()
        .duration(1000)
        .attr("x", function(d) { return x(d.group); })
        .attr("y", function(d) { return y(d.value); })
        .attr("width", x.bandwidth())
        .attr("height", function(d) { return height - y(d.value); })
        .attr("fill", "#69b3a2")

}
