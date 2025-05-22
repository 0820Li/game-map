# Mesh Generator

This is a copy of my course project of SFWRENG 2AA4, McMaster University.
I uploaded the copy because the original repository are set to be private by organization.

All files in this repository are used for my resume only.

Link of original repo: https://github.com/2AA4-W23/a4-urbanism-0820Li

Authors:

- Zongcheng Li (li1200@mcmaster.ca)
- Jerry Jing (jingz2@mcmaster.ca)
- Sébastien Mosser (Instructor)

## How to install?

```
mosser@azrael A2 % mvn install
```

It creates two jars:

  1. `generator/generator.jar` to generate meshes
  2. `visualizer/visualizer.jar` to visualize such meshes as SVG files

## Examples of execution

### Generating a mesh, grid or irregular

```
mosser@azrael A2 % java -jar generator/generator.jar -k grid -h 1080 -w 1920 -p 1000 -s 20 -o img/grid.mesh
mosser@azrael A2 % java -jar generator/generator.jar -k irregular -h 1080 -w 1920 -p 1000 -s 20 -o img/irregular.mesh
```

One can run the generator with `-help` as option to see the different command line arguments that are available

### Generating an island mesh

```
mosser@azrael A2 % java -jar island/island.jar -i img/irregular.mesh -o img/island.mesh -s hourglass -a volcano -l 3 -q 1 -t 10
mosser@azrael A2 % java -jar island/island.jar -i img/irregular.mesh -o img/island.mesh -s circle -a artic -l 5 -q 2 -t 10
```

One can run the generator with `-help` as option to see the different command line arguments that are available

### Visualizing a mesh, (regular or debug mode)

```
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/grid.mesh -o img/grid.svg          
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/grid.mesh -o img/grid_debug.svg -x
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/island.mesh -o img/irregular.svg   
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/island.mesh -o img/irregular_debug.svg -x
```

Note: PDF versions of the SVG files were created with `rsvg-convert`.

### Pathfinder

Authors:

- Zongcheng Li (li1200@mcmaster.ca)

This project includes code for a graphADT and a set of pathfinding algorithms that can work on the graph.

To add more algorithms to the project, you can add a new class that implements the `PathFindingAlgorithm` interface and takes a class implementing `Graph<N, E>` as an argument.

## Island Backlog - Updated 2023/03/28

| MVP? | Id  | Feature                  | Status  | Started    | Delivered  |
|------|-----|--------------------------|---------|------------|------------|
|      | 1   | Command line             | Done    | 2023/03/28 | 2023/03/28 |
| Yes  | 3   | Basic Shape              | Done    | 2023/03/28 | 2023/03/28 |
|      | 4   | Heatmap                  | Done    | 2023/03/28 | 2023/03/28 |
|      | 5   | Aquifers                 | Done    | 2023/03/28 | 2023/03/28 |
|      | 6   | Basic Elevation          | Done    | 2023/03/28 | 2023/03/28 |
|      | 7   | Advanced Shape           | Done    | 2023/03/28 | 2023/03/28 |
| Yes  | 8   | Lakes                    | Done    | 2023/03/28 | 2023/03/28 |
|      | 9   | Rivers                   | Pending |            |            |
|      | 10  | Advanced Elevation       | Done    | 2023/03/28 | 2023/03/28 |
|      | 11  | Basic Soil Absorption    | Done    | 2023/03/28 | 2023/03/28 |
| Yes  | 12  | Biomes                   | Done    | 2023/03/28 | 2023/03/28 |
|      | 13  | Advanced Soil Absorption | Pending |            |            |
|      | 14  | River Flow               | Pending |            |            |
|      | 15  | Resource Production      | Pending |            |            |
|      | 16  | Advanced Biomes          | Pending |            |            |
|      | 17  | Unit Test                | Pending |            |            |

## Pathfinder Backlog - Updated 2023/04/08
| MVP? | Id  | Feature                  | Status  | Started    | Delivered  |
|------|-----|--------------------------|---------|------------|------------|
|Yes| 1   | add pathfinder project   | Done    | 2023/04/04 | 2023/04/04 |
|Yes | 2   | graph                    | Done    | 2023/04/04 | 2023/04/04 |
|Yes| 3   | path finding algorithm   | Done    | 2023/04/04 | 2023/04/04 |
|      | 4   |path finding algorithm test| Done    | 2023/04/04 | 2023/04/04 |
|      | 5   | add pathfinder as dependency  | Done    | 2023/04/04 | 2023/04/04 |
|      | 6   |add command line opetion| Done    | 2023/04/04 | 2023/04/04 |
|      | 7   |generate cities| Done    | 2023/04/04 | 2023/04/04 |
|      | 8   |visualizer draw segments and vertices| Done    | 2023/04/04 | 2023/04/04 |
|      | 9   |draw cities and rodes| Done    | 2023/04/04 | 2023/04/04 |

