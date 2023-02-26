public class DistanceGridValue {

    private String pointName;
    private Point point;
    private Point centroid;
    private Double distance;
    private Integer groupedCluster;

    public DistanceGridValue(String pointName, Point point, Point centroid, Double distance) {
        this.pointName = pointName;
        this.point = point;
        this.centroid = centroid;
        this.distance = distance;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getGroupedCluster() {
        return groupedCluster;
    }

    public void setGroupedCluster(Integer groupedCluster) {
        this.groupedCluster = groupedCluster;
    }
}
