recfwk
Recommender Evaluation Framework
    
Overview

This software framework provides basic code for helping the process of designing and implementing recommender engines.

Documentation

API/Javadoc documentation is distributed with the source package and is also available online.

Details

When studying recommender algorithms it is necessary to carry on repetitive tasks such as validating results with multiple subsets of training data so as to increase results confidence, representing results graphically in several ways as an aid for analysis etc.

Thus it becomes necessary to have an infrastructure in terms of tools, reusable code modules, procedures and data standards, all of which could be represented as a framework, which is described in this section. The following diagram shows the data flow and main processes necessary for such a framework.

The framework can be broken down into these main concepts:

Input: responsible for loading data describing relationship among entities on a given social network. Such loading can have as a source pure text files, YAML, XML or relational databases. An extension of the data input interface can also read relationships using open standards for describing social media. Such queries to RDF databases can be done using the SPARQL language for item selection for example.

Filter: Applies a filter over input data in order to make projections (reduce data dimensionality), summarize, fill missing data with averages, add noise, normalize ratings, perform data type transformations, convert unique domain-specific IDs into sequential integers, which some recommending engines take as input etc. Several filters can be composed in order to achieve more complex data transformations. Write data to a file: Persists filtered input data or experiment results to disk for later processing, graphical representation etc.

Measure performance: based on input data and its attributes (total volume, training rate etc), and the recommendations made, this process should calculate several performance measures such as recall, precision and its derivatives (like F-measure), medium absolute error, medium square error etc Train recommender: Establishes a base interface for training and managing the progress of this process. Plot results: Fed with experiments performance data, input metadata (training ratio, total volume, specific parameters etc), this process is responsible for plotting simple graphics, histograms or scatter plots for multivariate analysis.

Save and read training: Persists on and reads from disk a learned model for the recommender. This process offers few possibilities for extensions due to the high degree of variation found on serialization needs for each recommender algorithm.

Recommend: Returns recommended items based on a previously learned recommendation model.