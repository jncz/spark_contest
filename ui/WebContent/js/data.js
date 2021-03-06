/*
 * Real-time data generators for the example graphs in the documentation section.
 */
(function() {

    /*
     * Class for generating real-time data for the area, line, and bar plots.
     */
    var RealTimeData = function(layers) {
        this.layers = layers;
        this.timestamp = ((new Date()).getTime() / 1000)|0;
    };

    RealTimeData.prototype.rand = function() {
        return parseInt(Math.random() * 100) + 50;
    };

    RealTimeData.prototype.history = function(entries) {
        if (typeof(entries) != 'number' || !entries) {
            entries = 60;
        }

        var history = [];
        for (var k = 0; k < this.layers; k++) {
            history.push({ values: [] });
        }

        for (var i = 0; i < entries; i++) {
            for (var j = 0; j < this.layers; j++) {
            	history[j].label = 'Series' + j;
                history[j].values.push({time: this.timestamp, y: 10});
            }
            this.timestamp++;
        }

        return history;
    };

    RealTimeData.prototype.next = function() {
        var entry = [];
        for (var i = 0; i < this.layers; i++) {
            entry.push({ time: this.timestamp, y: this.rand() });
        }
        this.timestamp++;
        console.info(entry);
        return entry;
    };

    window.RealTimeData = RealTimeData;


    /*
     * Gauge Data Generator.
     */
    var GaugeData = function() {};

    GaugeData.prototype.next = function() {
        return Math.random();
    };

    window.GaugeData = GaugeData;



    /*
     * Heatmap Data Generator.
     */

    var HeatmapData = function(layers) {
        this.layers = layers;
        this.timestamp = ((new Date()).getTime() / 1000)|0;
    };
    
    window.normal = function() {
        var U = Math.random(),
            V = Math.random();
        return Math.sqrt(-2*Math.log(U)) * Math.cos(2*Math.PI*V);
    };

})();
