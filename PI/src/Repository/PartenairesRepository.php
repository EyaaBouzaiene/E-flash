<?php

namespace App\Repository;

use App\Entity\Partenaires;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\OptimisticLockException;
use Doctrine\ORM\ORMException;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Partenaires|null find($id, $lockMode = null, $lockVersion = null)
 * @method Partenaires|null findOneBy(array $criteria, array $orderBy = null)
 * @method Partenaires[]    findAll()
 * @method Partenaires[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class PartenairesRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Partenaires::class);
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function add(Partenaires $entity, bool $flush = true): void
    {
        $this->_em->persist($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function remove(Partenaires $entity, bool $flush = true): void
    {
        $this->_em->remove($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    public function alphabet(){
    $entityManager=$this->getEntityManager();
    $query=$entityManager
        ->createQuery("SELECT p.nomP  FROM APP\Entity\Partenaires p JOIN  WHERE p.nomP=:name ")
        ->setParameter('name',$name);
        return $query->getSingleScalarResult();
}


   

    // /**
    //  * @return Partenaires[] Returns an array of Partenaires objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('p.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Partenaires
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */



    /**
    * @return Partenaires[]
    */
   public function findOneByIdJoinedToCategory(int $qte_s): ?Partenaires
    {
        $entityManager = $this->getEntityManager();

        $query = $entityManager->createQuery(
            'SELECT p, s
            FROM App\Entity\Partenaires p
            INNER JOIN p.category c
            WHERE p.id = :id'
        )->setParameter('id', $productId);

        return $query->getOneOrNullResult();
    }

################################################  ###########################################################################"" 

/* 
    public function selectmail()
    {
     return $this->getEntityManager()
     
         ->createQuery('SELECT p FROM App\Entity\Partenaires p WHERE  p.rate < 3 ')
        
             ->getResult(); 
 
    }

################################################ mail dont la qualite<3 ###########################################################################"" 

    public function selectmail1()
    {
     return $this->getEntityManager()
     
         ->createQuery('SELECT p.mailP FROM App\Entity\Partenaires p WHERE  p.rate < 3 ')
        
             ->getResult(); 
 
    } */

################################################ nomMarque dont la qualite<3: MAIL ###########################################################################"" 

    public function selectMarque()
    {
     return $this->getEntityManager()
     
         ->createQuery('SELECT p.nomMarqueP FROM App\Entity\Partenaires p,App\Entity\Stock2 s 
          WHERE s.partenaire =p.id
         and s.qualiteS =5 ')
        
             ->getResult(); 
 
    }
 
################################################ FRONT MEILLEUR PARTENAIRES  ###########################################################################"" 
    

    public function meilleurPartenaire()
    {
   
   
        return $this->getEntityManager()
     ->createQuery(
         'SELECT p.nomMarqueP as Marque ,s.qualiteS as qualite1
         FROM App\Entity\Stock2 s ,App\Entity\Partenaires p
         WHERE s.partenaire =p.id
         and s.qualiteS =5'
     )
     
     ->getResult();

         
 
     }
################################################ TRI PARTENAIRE DESC PAR DATE  ###########################################################################"" 

   public function TRIEpart()
   {
  
  
    return $this->createQueryBuilder('p')
    ->addOrderBy('p.dateAjout', 'desc')
    ->getQuery()
    ->getResult()
;
        

    }

    
    
}
