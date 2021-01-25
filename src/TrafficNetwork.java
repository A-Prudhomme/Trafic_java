import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;


public class TrafficNetwork {
    Node[][] nodes;
    ArrayList<SubNode> subnodes;
    ArrayList<Vehicle> vehicles;


    public TrafficNetwork(int nbNode, int nbSubnode) {
        this.nodes = new Node[nbNode][nbNode];
        this.vehicles = new ArrayList<>();
        double horizontal = 600 / nbNode;
        double vertical = 600 / nbNode;
        for (int i = 0; i < nbNode; i ++){
            for (int j = 0; j < nbNode; j ++){
                nodes[i][j] = new Node(i * horizontal + horizontal/2, j * vertical + vertical/2);
                if(i == 0) {
                    vehicles.add(new Vehicle(i * horizontal + horizontal / 2, j * vertical + vertical / 2, 15, true, new int[]{i, j}));
                }
                if(i != 0 && j == nbNode - 1){
                    vehicles.add(new Vehicle(i * horizontal + horizontal / 2, j * vertical + vertical / 2, 15, false, new int[]{i, j}));
                }
            }
        }
        this.subnodes = new ArrayList<>();
        double horizontal_nodes = horizontal / nbSubnode;
        double vertical_nodes = vertical / nbSubnode;
        for (int i = 0; i < nbNode - 1; i ++){
            for (int j = 1; j < nbNode; j ++){
                for (int k = 0; k < nbSubnode; k ++) {
                    subnodes.add(new SubNode(nodes[i][j-1].getX() + horizontal_nodes / 2 + horizontal_nodes * k, nodes[i][j - 1].getY(), nodes[i + 1][j - 1], null));
                    subnodes.add(new SubNode(nodes[i][j - 1].getX(), nodes[i][j - 1].getY() + vertical_nodes / 2 + vertical_nodes * k, null, nodes[i][j - 1]));
                }
            }
        }
        for (int i = 0; i < nbNode - 1; i ++){
            for (int k = 0; k < nbSubnode; k ++) {
                subnodes.add(new SubNode(nodes[nbNode - 1][i].getX(), nodes[nbNode - 1][i].getY() + vertical_nodes / 2 + vertical_nodes * k, null, nodes[nbNode - 1][i]));
            }
        }
        for (int i = 0; i < nbNode - 1; i ++){
            for (int k = 0; k < nbSubnode; k ++) {
                subnodes.add(new SubNode(nodes[i][nbNode - 1].getX() + horizontal_nodes / 2 + horizontal_nodes * k, nodes[i][nbNode - 1].getY(), nodes[i][nbNode - 1], null));
            }
        }
    }

    public Node[][] getNodes() {
        return nodes;
    }

    public ArrayList<SubNode> getSubnodes() {
        return subnodes;
    }

    public ArrayList<Vehicle> getVehicles() {
        ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
        for(Vehicle vehicle : vehicles){
            if (!vehicle.getDone()){
                cars.add(vehicle);
            }
        }
        return cars;
    }

    public void vehicleForward(int nbNode, int nbSubnode){
/*
        this.vehicles = vehicles.stream().filter(Vehicle -> !Vehicle.getDone()));
        .stream().filter(Vehicle -> !Vehicle.getDone())
*/
        for(Vehicle vehicle : vehicles){
            Node actual_node = nodes[vehicle.getNode()[0]][vehicle.getNode()[1]];
            if(vehicle.getHorizontal()){
                if(vehicle.getNode()[0] < nbNode - 1) {
                    Node next_node = nodes[vehicle.getNode()[0]+1][vehicle.getNode()[1]];
                    if (vehicle.subnode == null){
                        Optional<SubNode> greater_subnodes = this.subnodes.stream().filter(subNode -> subNode.getX() > actual_node.getX() && subNode.getY() == actual_node.getY()).min(Comparator.comparing(SubNode::getX));
                        greater_subnodes.ifPresent(subNode -> vehicle.subnode = subNode);
                        vehicle.setPosition();
                    }
                    else {
                        Optional<SubNode> greater_subnodes = subnodes.stream().filter(subNode -> subNode.getX() > vehicle.subnode.getX() && subNode.getX() < next_node.getX() && subNode.getY() == vehicle.subnode.getY()).min(Comparator.comparing(SubNode::getX));
                        if(greater_subnodes.isEmpty()){
                            vehicle.setNode(new int[]{vehicle.getNode()[0] + 1, vehicle.getNode()[1]});
                            vehicle.setPosition(next_node);
                            vehicle.subnode = null;
                        }
                        else {
                            vehicle.subnode = greater_subnodes.get();
                            vehicle.setPosition();
                        }
                    }
                }
                else{
                    vehicle.done();
                }
            }
            if(!vehicle.getHorizontal()){
                if(vehicle.getNode()[1] != 0) {
                    Node next_node = nodes[vehicle.getNode()[0]][vehicle.getNode()[1] - 1];
                    if (vehicle.subnode == null){
                        Optional<SubNode> greater_subnodes = subnodes.stream().filter(subNode -> subNode.getX() == actual_node.getX() && subNode.getY() < actual_node.getY()).max(Comparator.comparing(SubNode::getY));
                        greater_subnodes.ifPresent(subNode -> vehicle.subnode = subNode);
                        vehicle.setPosition();
                    }
                    else {
                        Optional<SubNode> greater_subnodes = subnodes.stream().filter(subNode -> subNode.getX() == vehicle.subnode.getX() && subNode.getY() > next_node.getY() && subNode.getY() < vehicle.subnode.getY()).max(Comparator.comparing(SubNode::getY));
                        if(greater_subnodes.isEmpty()){
                            vehicle.setNode(new int[]{vehicle.getNode()[0], vehicle.getNode()[1] - 1});
                            vehicle.setPosition(next_node);
                            vehicle.subnode = null;
                        }
                        else {
                            vehicle.subnode = greater_subnodes.get();
                            vehicle.setPosition();
                        }
                    }
                }
                else{
                    vehicle.done();
                }
            }
        }
    }


}
